/**
 * Copyright (C) 2014 АйТи http://it.ru <info@it.ru>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanka.goodcauses.rest.resources;

import com.yanka.goodcauses.model.BaseDBObject;
import com.yanka.goodcauses.model.GenericEntity;

import org.springframework.beans.support.PagedListHolder;

public interface ResourceCallback<T extends BaseDBObject> {

    PagedListHolder<T> invokeCreate();
}
