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

import org.opencloudengine.garuda.web.configuration.ConfigurationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

/**
 * @author Seungpil PARK
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ConfigurationHelper configurationHelper;

    @Override
    public List<ProductMix> getAllProductMix() {
        return productRepository.selectAllProductMix();
    }

    @Override
    public List<ProductInterception> getAllProductInterception() {
        return productRepository.selectAllProductInterception();
    }

    @Override
    public List<ProductMix> getProductMixByCondition(ProductMix condition) {
        return productRepository.selectProductMixByCondition(condition);
    }

    @Override
    public List<ProductFeatures> getFeaturesByProductFamilyId(Long productFamilyId) {
        return productRepository.selectProductFeaturesByProductFamilyId(productFamilyId);
    }

    @Override
    public ProductMix selectProductMixByProductId(Long productId) {
        return productRepository.selectProductMixByProductId(productId);
    }
}
