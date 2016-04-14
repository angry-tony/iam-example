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

import org.opencloudengine.garuda.common.repository.PersistentRepository;

import java.util.List;

/**
 * 사용자 빌링정보에 대한 CRUD 기능을 처리하는 UserBilling Repository
 *
 * @author Seungpil PARK
 */
public interface ProductRepository extends PersistentRepository<String, Object> {

    public static final String NAMESPACE = ProductRepository.class.getName();

    List<ProductMix> selectAllProductMix();

    List<ProductInterception> selectAllProductInterception();

    List<ProductMix> selectProductMixByCondition(ProductMix condition);

    List<ProductFeatures> selectProductFeaturesByProductFamilyId(Long productFamilyId);

    ProductMix selectProductMixByProductId(Long productId);
}
