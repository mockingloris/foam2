/**
 * @license
 * Copyright 2016 Google Inc. All Rights Reserved.
 * Copyright 2017 The FOAM Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

foam.INTERFACE({
  refines: 'foam.blob.Blob',

  methods: [
    {
      name: 'read',
      javaReturns: 'java.nio.Buffer',
      args: [
        {
          name: 'buffer',
          javaType: 'java.nio.Buffer'
        },
        {
          name: 'offset',
          javaType: 'long'
        }
      ]
    }
  ]
});

foam.INTERFACE({
  refines: 'foam.blob.BlobService',

  methods: [
    {
      name: 'put',
      javaReturns: 'foam.blob.Blob',
      args: [
        {
          name: 'blob',
          javaType: 'foam.blob.Blob'
        }
      ]
    },
    {
      name: 'put_',
      javaReturns: 'foam.blob.Blob',
      args: [
        {
          name: 'x',
          javaType: 'foam.core.X'
        },
        {
          name: 'blob',
          javaType: 'foam.blob.Blob'
        }
      ]
    },
    {
      name: 'find',
      javaReturns: 'foam.blob.Blob',
      args: [
        {
          name: 'id',
          javaType: 'String'
        }
      ]
    },
    {
      name: 'find_',
      javaReturns: 'foam.blob.Blob',
      args: [
        {
          name: 'x',
          javaType: 'foam.core.X'
        },
        {
          name: 'id',
          javaType: 'String'
        }
      ]
    },
    {
      name: 'urlFor',
      javaReturns: 'String',
      args: [
        {
          name: 'blob',
          javaType: 'foam.blob.Blob'
        }
      ]
    }
  ]
});