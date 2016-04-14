/**
 * Copyright (C) 2011 Flamingo Project (http://www.opencloudengine.org).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.opencloudengine.garuda.web.product;

import org.opencloudengine.garuda.common.repository.PersistentRepositoryImpl;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Seungpil PARK
 */
@Repository
public class ProductRepositoryImpl extends PersistentRepositoryImpl<String, Object> implements ProductRepository {

    @Override
    public String getNamespace() {
        return NAMESPACE;
    }

    @Autowired
    public ProductRepositoryImpl(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public List<ProductMix> selectAllProductMix() {
        return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectAllProductMix");
    }

    @Override
    public List<ProductInterception> selectAllProductInterception() {
        return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectAllProductInterception");
    }

    @Override
    public List<ProductMix> selectProductMixByCondition(ProductMix condition) {
        return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectProductMixByCondition", condition);
    }

    @Override
    public List<ProductFeatures> selectProductFeaturesByProductFamilyId(Long productFamilyId) {
        return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectProductFeaturesByProductFamilyId", productFamilyId);
    }

    @Override
    public ProductMix selectProductMixByProductId(Long productId) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectProductMixByProductId", productId);
    }
}