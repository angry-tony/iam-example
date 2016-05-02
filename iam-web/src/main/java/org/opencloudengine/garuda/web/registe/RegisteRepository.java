package org.opencloudengine.garuda.web.registe;

public interface RegisteRepository {

    Registe selectByUserIdAndToken(Registe registe);

    Registe insert(Registe registe);

}
