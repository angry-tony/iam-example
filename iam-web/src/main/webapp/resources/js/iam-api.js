/**
 * Created by Seungpil Park on 2016. 9. 6..
 */
var IAM = function (host, port) {
    this.host = host;
    this.port = port;
    this.schema = 'http';
    if (!host && !port) {
        this.baseUrl = '';
    } else {
        this.baseUrl = this.schema + '://' + this.host + ':' + this.port;
    }
    this.user = undefined;
    this.management = undefined;

    $(document).ajaxSend(function (e, xhr, options) {
        var token = localStorage.getItem('uengine-iam-access-token');
        var managementKey = localStorage.getItem('uengine-iam-management-key');
        var managementSecret = localStorage.getItem('uengine-iam-management-secret');
        xhr.setRequestHeader('Authorization', token);
        xhr.setRequestHeader('management-key', managementKey);
        xhr.setRequestHeader('management-secret', managementSecret);
    });
};
IAM.prototype = {
    logout: function () {
        localStorage.removeItem('uengine-iam-access-token');
        localStorage.removeItem('uengine-iam-management-id');
        localStorage.removeItem('uengine-iam-management-key');
        localStorage.removeItem('uengine-iam-management-secret');
    },
    setDefaultManagement: function (id, key, secret) {
        localStorage.setItem('uengine-iam-management-id', id);
        localStorage.setItem('uengine-iam-management-key', key);
        localStorage.setItem('uengine-iam-management-secret', secret);
    },
    getDefaultManagement: function () {
        return localStorage.getItem('uengine-iam-management-id');
    },

    login: function (data) {
        var me = this;
        var username = data.username;
        var password = data.password;
        var deferred = $.Deferred();
        var promise = $.ajax({
            type: "POST",
            url: me.baseUrl + '/rest/v1/access_token',
            data: 'username=' + username + '&password=' + password,
            contentType: "application/x-www-form-urlencoded",
            dataType: "json"
        });
        promise.done(function (response) {
            if (response['access_token']) {
                console.log('login success');
                var token = response['access_token'];
                localStorage.setItem("uengine-iam-access-token", token);
                deferred.resolve(response);
            } else {
                console.log('login failed');
                localStorage.removeItem("uengine-iam-access-token");
                deferred.reject();
            }
        });
        promise.fail(function (response, status, errorThrown) {
            console.log('login failed', errorThrown, response.responseText);
            localStorage.removeItem("uengine-iam-access-token");
            deferred.reject(response);
        });
        return deferred.promise();
    },
    validateToken: function () {
        console.log('Validating token...');
        var me = this;
        var token = localStorage.getItem("uengine-iam-access-token");
        var deferred = $.Deferred();
        var promise = $.ajax({
            type: "GET",
            url: me.baseUrl + '/rest/v1/token_info?authorization=' + token,
            dataType: "json",
            async: false
        });
        promise.done(function (response) {
            console.log('validateToken success');
            deferred.resolve(response);
        });
        promise.fail(function (response, status, errorThrown) {
            console.log('validateToken failed', errorThrown, response.responseText);
            deferred.reject(response);
        });
        return deferred.promise();
    },
    getManagements: function () {
        var options = {
            type: "GET",
            url: '/rest/v1/management',
            dataType: "json",
            async: false
        };
        return this.send(options);
    },
    createManagement: function (data) {
        var options = {
            type: "POST",
            url: '/rest/v1/management',
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: 'text',
            resolve: function (response, status, xhr) {
                var locationHeader = xhr.getResponseHeader('Location');
                return locationHeader.substring(locationHeader.lastIndexOf('/') + 1);
            }
        };
        return this.send(options);
    },
    updateManagement: function (id, data) {
        var options = {
            type: "PUT",
            url: '/rest/v1/management/' + id,
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json"
        };
        return this.send(options);
    },
    deleteManagement: function (id) {
        var options = {
            type: "DELETE",
            url: '/rest/v1/management/' + id
        };
        return this.send(options);
    },
    getAllUsers: function () {
        var options = {
            type: "GET",
            url: '/rest/v1/user',
            dataType: "json"
        };
        return this.send(options);
    },
    getUser: function (id) {
        var options = {
            type: "GET",
            url: '/rest/v1/user/' + id,
            dataType: "json"
        };
        return this.send(options);
    },
    getUserSearch: function (searchKey, offset, limit) {
        var data = {
            offset: offset ? offset : 0,
            limit: limit ? limit : 100,
            audit: 'NONE'
        };
        var url = searchKey ? '/rest/v1/user/search/' + searchKey : '/rest/v1/user/pagination';
        var options = {
            type: "GET",
            url: url,
            dataType: 'json',
            data: data,
            resolve: function (response, status, xhr) {
                var total = parseInt(xhr.getResponseHeader('x-killbill-pagination-totalnbrecords'));
                var filtered = parseInt(xhr.getResponseHeader('x-killbill-pagination-maxnbrecords'));
                return {
                    data: response,
                    total: total,
                    filtered: filtered,
                    offset: data.offset,
                    limit: data.limit
                };
            }
        };
        return this.send(options);
    },
    createUser: function (data) {
        var options = {
            type: "POST",
            url: '/rest/v1/user',
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: 'text',
            resolve: function (response, status, xhr) {
                var locationHeader = xhr.getResponseHeader('Location');
                return locationHeader.substring(locationHeader.lastIndexOf('/') + 1);
            }
        };
        return this.send(options);
    },
    updateUser: function (id, data) {
        var options = {
            type: "PUT",
            url: '/rest/v1/user/' + id,
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json"
        };
        return this.send(options);
    },
    deleteUser: function (id) {
        var options = {
            type: "DELETE",
            url: '/rest/v1/user/' + id
        };
        return this.send(options);
    },
    send: function (options) {
        var caller = arguments.callee.caller.name;
        var me = this;
        var deferred = $.Deferred();
        var ajaxOptions = {
            type: options.type,
            url: me.baseUrl + options.url
        };
        if (options.dataType) {
            ajaxOptions.dataType = options.dataType;
        }
        if (options.contentType) {
            ajaxOptions.contentType = options.contentType;
        }
        if (typeof options.async == 'boolean' && !options.async) {
            ajaxOptions.async = false;
        }
        if (options.data) {
            ajaxOptions.data = options.data;
        }
        var promise = $.ajax(ajaxOptions);
        promise.done(function (response, status, xhr) {
            console.log(caller + ' success');
            if (options.resolve) {
                response = options.resolve(response, status, xhr);
            }
            deferred.resolve(response);
        });
        promise.fail(function (response, status, errorThrown) {
            console.log(caller + ' failed');
            console.log(response, status, errorThrown);
            if (options.reject) {
                response = options.reject(response, status, errorThrown);
            }
            deferred.reject(response);
        });
        return deferred.promise();
    }
}
;
IAM.prototype.constructor = IAM;