---
title: API de HealthTuring v1.0
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
highlight_theme: darkula
headingLevel: 2

---

<!-- Generator: Widdershins v4.0.1 -->

<h1 id="api-de-healthturing">API de HealthTuring v1.0</h1>

> Scroll down for code samples, example requests and responses. Select a language for code samples from the tabs above or the mobile navigation menu.

Documentación de la API de mi aplicación Spring Boot HealthTuring

Base URLs:

* <a href="http://localhost:8080">http://localhost:8080</a>

# Authentication

- HTTP Authentication, scheme: bearer 

<h1 id="api-de-healthturing-doc-controller">doc-controller</h1>

## editTreatment

<a id="opIdeditTreatment"></a>

> Code samples

```shell
# You can also use wget
curl -X PUT http://localhost:8080/api/doctor/edit-treatment/{id} \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
PUT http://localhost:8080/api/doctor/edit-treatment/{id} HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = '{
  "reason": "string",
  "startDate": "2019-08-24",
  "endDate": "2019-08-24",
  "dosesPerPeriod": "string"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/doctor/edit-treatment/{id}',
{
  method: 'PUT',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.put 'http://localhost:8080/api/doctor/edit-treatment/{id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.put('http://localhost:8080/api/doctor/edit-treatment/{id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PUT','http://localhost:8080/api/doctor/edit-treatment/{id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/doctor/edit-treatment/{id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PUT");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PUT", "http://localhost:8080/api/doctor/edit-treatment/{id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PUT /api/doctor/edit-treatment/{id}`

> Body parameter

```json
{
  "reason": "string",
  "startDate": "2019-08-24",
  "endDate": "2019-08-24",
  "dosesPerPeriod": "string"
}
```

<h3 id="edittreatment-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer(int64)|true|none|
|body|body|[TreatmentUpdateDTO](#schematreatmentupdatedto)|true|none|

> Example responses

> 200 Response

<h3 id="edittreatment-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ApiResponseDTO](#schemaapiresponsedto)|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## createTreatment

<a id="opIdcreateTreatment"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/doctor/create-treatment \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST http://localhost:8080/api/doctor/create-treatment HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = '{
  "reason": "string",
  "startDate": "2019-08-24",
  "endDate": "2019-08-24",
  "dosesPerPeriod": "string",
  "patientId": 0,
  "medicamentId": 0
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/doctor/create-treatment',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'http://localhost:8080/api/doctor/create-treatment',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('http://localhost:8080/api/doctor/create-treatment', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/doctor/create-treatment', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/doctor/create-treatment");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/doctor/create-treatment", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/doctor/create-treatment`

> Body parameter

```json
{
  "reason": "string",
  "startDate": "2019-08-24",
  "endDate": "2019-08-24",
  "dosesPerPeriod": "string",
  "patientId": 0,
  "medicamentId": 0
}
```

<h3 id="createtreatment-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[TreatmentCreateDTO](#schematreatmentcreatedto)|true|none|

> Example responses

> 200 Response

<h3 id="createtreatment-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ApiResponseDTO](#schemaapiresponsedto)|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## deleteTreatment

<a id="opIddeleteTreatment"></a>

> Code samples

```shell
# You can also use wget
curl -X DELETE http://localhost:8080/api/doctor/delete-treatment/{id} \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
DELETE http://localhost:8080/api/doctor/delete-treatment/{id} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/doctor/delete-treatment/{id}',
{
  method: 'DELETE',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.delete 'http://localhost:8080/api/doctor/delete-treatment/{id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.delete('http://localhost:8080/api/doctor/delete-treatment/{id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','http://localhost:8080/api/doctor/delete-treatment/{id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/doctor/delete-treatment/{id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "http://localhost:8080/api/doctor/delete-treatment/{id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /api/doctor/delete-treatment/{id}`

<h3 id="deletetreatment-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer(int64)|true|none|

> Example responses

> 200 Response

<h3 id="deletetreatment-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ApiResponseDTO](#schemaapiresponsedto)|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

<h1 id="api-de-healthturing-auth-controller">auth-controller</h1>

## resetPassword

<a id="opIdresetPassword"></a>

> Code samples

```shell
# You can also use wget
curl -X PUT http://localhost:8080/api/auth/reset-password/{token} \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
PUT http://localhost:8080/api/auth/reset-password/{token} HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = 'string';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/auth/reset-password/{token}',
{
  method: 'PUT',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.put 'http://localhost:8080/api/auth/reset-password/{token}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.put('http://localhost:8080/api/auth/reset-password/{token}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PUT','http://localhost:8080/api/auth/reset-password/{token}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/auth/reset-password/{token}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PUT");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PUT", "http://localhost:8080/api/auth/reset-password/{token}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PUT /api/auth/reset-password/{token}`

> Body parameter

```json
"string"
```

<h3 id="resetpassword-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|token|path|string|true|none|
|body|body|string|true|none|

> Example responses

> 200 Response

<h3 id="resetpassword-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|string|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## emailConfirm

<a id="opIdemailConfirm"></a>

> Code samples

```shell
# You can also use wget
curl -X PUT http://localhost:8080/api/auth/email-confirmation \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
PUT http://localhost:8080/api/auth/email-confirmation HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = 'string';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/auth/email-confirmation',
{
  method: 'PUT',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.put 'http://localhost:8080/api/auth/email-confirmation',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.put('http://localhost:8080/api/auth/email-confirmation', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PUT','http://localhost:8080/api/auth/email-confirmation', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/auth/email-confirmation");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PUT");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PUT", "http://localhost:8080/api/auth/email-confirmation", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PUT /api/auth/email-confirmation`

> Body parameter

```json
"string"
```

<h3 id="emailconfirm-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|string|true|none|

> Example responses

> 200 Response

<h3 id="emailconfirm-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|string|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## register

<a id="opIdregister"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/auth/register \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST http://localhost:8080/api/auth/register HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = '{
  "email": "string",
  "name": "string",
  "password": "string",
  "doctor": true,
  "isDoctor": true
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/auth/register',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'http://localhost:8080/api/auth/register',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('http://localhost:8080/api/auth/register', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/auth/register', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/auth/register");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/auth/register", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/auth/register`

> Body parameter

```json
{
  "email": "string",
  "name": "string",
  "password": "string",
  "doctor": true,
  "isDoctor": true
}
```

<h3 id="register-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[RegisterRequestDTO](#schemaregisterrequestdto)|true|none|

> Example responses

> 200 Response

<h3 id="register-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|string|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## logout

<a id="opIdlogout"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/auth/logout \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST http://localhost:8080/api/auth/logout HTTP/1.1
Host: localhost:8080

```

```javascript

const headers = {
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/auth/logout',
{
  method: 'POST',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'http://localhost:8080/api/auth/logout',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('http://localhost:8080/api/auth/logout', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/auth/logout', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/auth/logout");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/auth/logout", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/auth/logout`

<h3 id="logout-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## login

<a id="opIdlogin"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/auth/login \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST http://localhost:8080/api/auth/login HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = '{
  "email": "string",
  "password": "string"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/auth/login',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'http://localhost:8080/api/auth/login',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('http://localhost:8080/api/auth/login', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/auth/login', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/auth/login");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/auth/login", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/auth/login`

> Body parameter

```json
{
  "email": "string",
  "password": "string"
}
```

<h3 id="login-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[LoginRequestDTO](#schemaloginrequestdto)|true|none|

> Example responses

> 200 Response

<h3 id="login-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|string|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## requestPasswordReset

<a id="opIdrequestPasswordReset"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/auth/forget-password \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST http://localhost:8080/api/auth/forget-password HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = 'string';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/auth/forget-password',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'http://localhost:8080/api/auth/forget-password',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('http://localhost:8080/api/auth/forget-password', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/auth/forget-password', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/auth/forget-password");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/auth/forget-password", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/auth/forget-password`

> Body parameter

```json
"string"
```

<h3 id="requestpasswordreset-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|string|true|none|

> Example responses

> 200 Response

<h3 id="requestpasswordreset-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|string|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## adminLogout

<a id="opIdadminLogout"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/auth/adminlogout \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST http://localhost:8080/api/auth/adminlogout HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/auth/adminlogout',
{
  method: 'POST',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'http://localhost:8080/api/auth/adminlogout',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('http://localhost:8080/api/auth/adminlogout', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/auth/adminlogout', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/auth/adminlogout");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/auth/adminlogout", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/auth/adminlogout`

> Example responses

> 200 Response

<h3 id="adminlogout-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|string|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

<h1 id="api-de-healthturing-patient-controller">patient-controller</h1>

## createPatientForUser

<a id="opIdcreatePatientForUser"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/patient/create/{userId} \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST http://localhost:8080/api/patient/create/{userId} HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = '{
  "name": "string",
  "dni": "string",
  "dateOfBirth": "2019-08-24",
  "gender": "M",
  "bloodGroup": "A",
  "rhFactor": "POSITIVE",
  "emergencyContact": "string"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/patient/create/{userId}',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'http://localhost:8080/api/patient/create/{userId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('http://localhost:8080/api/patient/create/{userId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/patient/create/{userId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/patient/create/{userId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/patient/create/{userId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/patient/create/{userId}`

> Body parameter

```json
{
  "name": "string",
  "dni": "string",
  "dateOfBirth": "2019-08-24",
  "gender": "M",
  "bloodGroup": "A",
  "rhFactor": "POSITIVE",
  "emergencyContact": "string"
}
```

<h3 id="createpatientforuser-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|userId|path|integer(int64)|true|none|
|body|body|[PatientCreateDTO](#schemapatientcreatedto)|true|none|

> Example responses

> 200 Response

<h3 id="createpatientforuser-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ApiResponseDTO](#schemaapiresponsedto)|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## getPatientsByUserId

<a id="opIdgetPatientsByUserId"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/patient/user-patients/{userId} \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET http://localhost:8080/api/patient/user-patients/{userId} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/patient/user-patients/{userId}',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'http://localhost:8080/api/patient/user-patients/{userId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('http://localhost:8080/api/patient/user-patients/{userId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/patient/user-patients/{userId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/patient/user-patients/{userId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/patient/user-patients/{userId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/patient/user-patients/{userId}`

<h3 id="getpatientsbyuserid-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|userId|path|integer(int64)|true|none|

> Example responses

> 200 Response

<h3 id="getpatientsbyuserid-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="getpatientsbyuserid-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[PatientDTO](#schemapatientdto)]|false|none|none|
|» id|integer(int64)|false|none|none|
|» name|string|false|none|none|
|» doctorId|integer(int64)|false|none|none|
|» doctorName|string|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## getDoctorByPatientId

<a id="opIdgetDoctorByPatientId"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/patient/doctor/{patientId} \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET http://localhost:8080/api/patient/doctor/{patientId} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/patient/doctor/{patientId}',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'http://localhost:8080/api/patient/doctor/{patientId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('http://localhost:8080/api/patient/doctor/{patientId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/patient/doctor/{patientId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/patient/doctor/{patientId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/patient/doctor/{patientId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/patient/doctor/{patientId}`

<h3 id="getdoctorbypatientid-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|patientId|path|integer(int64)|true|none|

> Example responses

> 200 Response

<h3 id="getdoctorbypatientid-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[DoctorDTO](#schemadoctordto)|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## getAllDataPatientId

<a id="opIdgetAllDataPatientId"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/patient/data/{patientId} \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET http://localhost:8080/api/patient/data/{patientId} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/patient/data/{patientId}',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'http://localhost:8080/api/patient/data/{patientId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('http://localhost:8080/api/patient/data/{patientId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/patient/data/{patientId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/patient/data/{patientId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/patient/data/{patientId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/patient/data/{patientId}`

<h3 id="getalldatapatientid-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|patientId|path|integer(int64)|true|none|

> Example responses

> 200 Response

<h3 id="getalldatapatientid-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[PatientDataDTO](#schemapatientdatadto)|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## getPatientsByDoctorId

<a id="opIdgetPatientsByDoctorId"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/doctor/user-patients-doctor/{doctorId} \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET http://localhost:8080/api/doctor/user-patients-doctor/{doctorId} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/doctor/user-patients-doctor/{doctorId}',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'http://localhost:8080/api/doctor/user-patients-doctor/{doctorId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('http://localhost:8080/api/doctor/user-patients-doctor/{doctorId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/doctor/user-patients-doctor/{doctorId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/doctor/user-patients-doctor/{doctorId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/doctor/user-patients-doctor/{doctorId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/doctor/user-patients-doctor/{doctorId}`

<h3 id="getpatientsbydoctorid-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|doctorId|path|integer(int64)|true|none|

> Example responses

> 200 Response

<h3 id="getpatientsbydoctorid-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="getpatientsbydoctorid-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[PatientDTO](#schemapatientdto)]|false|none|none|
|» id|integer(int64)|false|none|none|
|» name|string|false|none|none|
|» doctorId|integer(int64)|false|none|none|
|» doctorName|string|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

<h1 id="api-de-healthturing-appointment-controller">appointment-controller</h1>

## reserveAppointment

<a id="opIdreserveAppointment"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/api/appointments/reserve \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST http://localhost:8080/api/appointments/reserve HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = '{
  "patientId": 0,
  "doctorId": 0,
  "date": "2019-08-24",
  "startTime": {
    "hour": 0,
    "minute": 0,
    "second": 0,
    "nano": 0
  },
  "reason": "string"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/appointments/reserve',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'http://localhost:8080/api/appointments/reserve',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('http://localhost:8080/api/appointments/reserve', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/api/appointments/reserve', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/appointments/reserve");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/api/appointments/reserve", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /api/appointments/reserve`

> Body parameter

```json
{
  "patientId": 0,
  "doctorId": 0,
  "date": "2019-08-24",
  "startTime": {
    "hour": 0,
    "minute": 0,
    "second": 0,
    "nano": 0
  },
  "reason": "string"
}
```

<h3 id="reserveappointment-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[AppointmentRequestDTO](#schemaappointmentrequestdto)|true|none|

> Example responses

> 200 Response

<h3 id="reserveappointment-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|string|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## getAppointmentsByPatientId

<a id="opIdgetAppointmentsByPatientId"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/appointments/{patientId} \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET http://localhost:8080/api/appointments/{patientId} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/appointments/{patientId}',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'http://localhost:8080/api/appointments/{patientId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('http://localhost:8080/api/appointments/{patientId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/appointments/{patientId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/appointments/{patientId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/appointments/{patientId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/appointments/{patientId}`

<h3 id="getappointmentsbypatientid-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|patientId|path|integer(int64)|true|none|

> Example responses

> 200 Response

<h3 id="getappointmentsbypatientid-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="getappointmentsbypatientid-responseschema">Response Schema</h3>

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## getAppointmentsByDoctorId

<a id="opIdgetAppointmentsByDoctorId"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/appointments/doctor/{doctorId} \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET http://localhost:8080/api/appointments/doctor/{doctorId} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/appointments/doctor/{doctorId}',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'http://localhost:8080/api/appointments/doctor/{doctorId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('http://localhost:8080/api/appointments/doctor/{doctorId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/appointments/doctor/{doctorId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/appointments/doctor/{doctorId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/appointments/doctor/{doctorId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/appointments/doctor/{doctorId}`

<h3 id="getappointmentsbydoctorid-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|doctorId|path|integer(int64)|true|none|

> Example responses

> 200 Response

<h3 id="getappointmentsbydoctorid-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="getappointmentsbydoctorid-responseschema">Response Schema</h3>

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## getAvailableSlots

<a id="opIdgetAvailableSlots"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/appointments/doctor/{doctorId}/available-slots?date=2019-08-24 \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET http://localhost:8080/api/appointments/doctor/{doctorId}/available-slots?date=2019-08-24 HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/appointments/doctor/{doctorId}/available-slots?date=2019-08-24',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'http://localhost:8080/api/appointments/doctor/{doctorId}/available-slots',
  params: {
  'date' => 'string(date)'
}, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('http://localhost:8080/api/appointments/doctor/{doctorId}/available-slots', params={
  'date': '2019-08-24'
}, headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/appointments/doctor/{doctorId}/available-slots', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/appointments/doctor/{doctorId}/available-slots?date=2019-08-24");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/appointments/doctor/{doctorId}/available-slots", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/appointments/doctor/{doctorId}/available-slots`

<h3 id="getavailableslots-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|doctorId|path|integer(int64)|true|none|
|date|query|string(date)|true|none|

> Example responses

> 200 Response

<h3 id="getavailableslots-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="getavailableslots-responseschema">Response Schema</h3>

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

<h1 id="api-de-healthturing-users-admin-rest-controller">users-admin-rest-controller</h1>

## createAdminUser

<a id="opIdcreateAdminUser"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/admin/user \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST http://localhost:8080/admin/user HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*

```

```javascript
const inputBody = '{
  "email": "string",
  "name": "string",
  "password": "string",
  "doctor": true,
  "isDoctor": true
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/admin/user',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'http://localhost:8080/admin/user',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('http://localhost:8080/admin/user', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/admin/user', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/admin/user");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/admin/user", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /admin/user`

> Body parameter

```json
{
  "email": "string",
  "name": "string",
  "password": "string",
  "doctor": true,
  "isDoctor": true
}
```

<h3 id="createadminuser-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[RegisterRequestDTO](#schemaregisterrequestdto)|true|none|

> Example responses

> 200 Response

<h3 id="createadminuser-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|string|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## deleteUser

<a id="opIddeleteUser"></a>

> Code samples

```shell
# You can also use wget
curl -X DELETE http://localhost:8080/admin/user/{id} \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
DELETE http://localhost:8080/admin/user/{id} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/admin/user/{id}',
{
  method: 'DELETE',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.delete 'http://localhost:8080/admin/user/{id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.delete('http://localhost:8080/admin/user/{id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','http://localhost:8080/admin/user/{id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/admin/user/{id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "http://localhost:8080/admin/user/{id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /admin/user/{id}`

<h3 id="deleteuser-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer(int64)|true|none|

> Example responses

> 200 Response

<h3 id="deleteuser-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|string|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

<h1 id="api-de-healthturing-treatment-controller">treatment-controller</h1>

## getTreatmentsByPatientId

<a id="opIdgetTreatmentsByPatientId"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/treatments/{patientId} \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET http://localhost:8080/api/treatments/{patientId} HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/treatments/{patientId}',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'http://localhost:8080/api/treatments/{patientId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('http://localhost:8080/api/treatments/{patientId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/treatments/{patientId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/treatments/{patientId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/treatments/{patientId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/treatments/{patientId}`

<h3 id="gettreatmentsbypatientid-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|patientId|path|integer(int64)|true|none|

> Example responses

> 200 Response

<h3 id="gettreatmentsbypatientid-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="gettreatmentsbypatientid-responseschema">Response Schema</h3>

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## getTreatmentsByPatientIdPaged

<a id="opIdgetTreatmentsByPatientIdPaged"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/treatments/{patientId}/paged \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET http://localhost:8080/api/treatments/{patientId}/paged HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/treatments/{patientId}/paged',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'http://localhost:8080/api/treatments/{patientId}/paged',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('http://localhost:8080/api/treatments/{patientId}/paged', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/treatments/{patientId}/paged', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/treatments/{patientId}/paged");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/treatments/{patientId}/paged", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/treatments/{patientId}/paged`

<h3 id="gettreatmentsbypatientidpaged-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|patientId|path|integer(int64)|true|none|
|page|query|integer(int32)|false|none|
|size|query|integer(int32)|false|none|

> Example responses

> 200 Response

<h3 id="gettreatmentsbypatientidpaged-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="gettreatmentsbypatientidpaged-responseschema">Response Schema</h3>

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

<h1 id="api-de-healthturing-medicament-controller">medicament-controller</h1>

## getAllMedicaments

<a id="opIdgetAllMedicaments"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/api/medicaments \
  -H 'Accept: */*' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET http://localhost:8080/api/medicaments HTTP/1.1
Host: localhost:8080
Accept: */*

```

```javascript

const headers = {
  'Accept':'*/*',
  'Authorization':'Bearer {access-token}'
};

fetch('http://localhost:8080/api/medicaments',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => '*/*',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'http://localhost:8080/api/medicaments',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': '*/*',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('http://localhost:8080/api/medicaments', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => '*/*',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/api/medicaments', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/api/medicaments");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"*/*"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/api/medicaments", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /api/medicaments`

> Example responses

> 200 Response

<h3 id="getallmedicaments-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

<h3 id="getallmedicaments-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[MedicamentDTO](#schemamedicamentdto)]|false|none|none|
|» id|integer(int64)|false|none|none|
|» commonName|string|false|none|none|
|» dosageForm|string|false|none|none|
|» drugRoute|string|false|none|none|
|» strength|string|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

# Schemas

<h2 id="tocS_TreatmentUpdateDTO">TreatmentUpdateDTO</h2>
<!-- backwards compatibility -->
<a id="schematreatmentupdatedto"></a>
<a id="schema_TreatmentUpdateDTO"></a>
<a id="tocStreatmentupdatedto"></a>
<a id="tocstreatmentupdatedto"></a>

```json
{
  "reason": "string",
  "startDate": "2019-08-24",
  "endDate": "2019-08-24",
  "dosesPerPeriod": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|reason|string|false|none|none|
|startDate|string(date)|false|none|none|
|endDate|string(date)|false|none|none|
|dosesPerPeriod|string|false|none|none|

<h2 id="tocS_ApiResponseDTO">ApiResponseDTO</h2>
<!-- backwards compatibility -->
<a id="schemaapiresponsedto"></a>
<a id="schema_ApiResponseDTO"></a>
<a id="tocSapiresponsedto"></a>
<a id="tocsapiresponsedto"></a>

```json
{
  "message": "string",
  "id": 0
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|message|string|false|none|none|
|id|integer(int64)|false|none|none|

<h2 id="tocS_PatientCreateDTO">PatientCreateDTO</h2>
<!-- backwards compatibility -->
<a id="schemapatientcreatedto"></a>
<a id="schema_PatientCreateDTO"></a>
<a id="tocSpatientcreatedto"></a>
<a id="tocspatientcreatedto"></a>

```json
{
  "name": "string",
  "dni": "string",
  "dateOfBirth": "2019-08-24",
  "gender": "M",
  "bloodGroup": "A",
  "rhFactor": "POSITIVE",
  "emergencyContact": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|name|string|false|none|none|
|dni|string|false|none|none|
|dateOfBirth|string(date)|false|none|none|
|gender|string|false|none|none|
|bloodGroup|string|false|none|none|
|rhFactor|string|false|none|none|
|emergencyContact|string|false|none|none|

#### Enumerated Values

|Property|Value|
|---|---|
|gender|M|
|gender|F|
|gender|O|
|bloodGroup|A|
|bloodGroup|B|
|bloodGroup|AB|
|bloodGroup|O|
|rhFactor|POSITIVE|
|rhFactor|NEGATIVE|

<h2 id="tocS_TreatmentCreateDTO">TreatmentCreateDTO</h2>
<!-- backwards compatibility -->
<a id="schematreatmentcreatedto"></a>
<a id="schema_TreatmentCreateDTO"></a>
<a id="tocStreatmentcreatedto"></a>
<a id="tocstreatmentcreatedto"></a>

```json
{
  "reason": "string",
  "startDate": "2019-08-24",
  "endDate": "2019-08-24",
  "dosesPerPeriod": "string",
  "patientId": 0,
  "medicamentId": 0
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|reason|string|false|none|none|
|startDate|string(date)|false|none|none|
|endDate|string(date)|false|none|none|
|dosesPerPeriod|string|false|none|none|
|patientId|integer(int64)|false|none|none|
|medicamentId|integer(int64)|false|none|none|

<h2 id="tocS_RegisterRequestDTO">RegisterRequestDTO</h2>
<!-- backwards compatibility -->
<a id="schemaregisterrequestdto"></a>
<a id="schema_RegisterRequestDTO"></a>
<a id="tocSregisterrequestdto"></a>
<a id="tocsregisterrequestdto"></a>

```json
{
  "email": "string",
  "name": "string",
  "password": "string",
  "doctor": true,
  "isDoctor": true
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|email|string|false|none|none|
|name|string|false|none|none|
|password|string|false|none|none|
|doctor|boolean|false|none|none|
|isDoctor|boolean|false|none|none|

<h2 id="tocS_LoginRequestDTO">LoginRequestDTO</h2>
<!-- backwards compatibility -->
<a id="schemaloginrequestdto"></a>
<a id="schema_LoginRequestDTO"></a>
<a id="tocSloginrequestdto"></a>
<a id="tocsloginrequestdto"></a>

```json
{
  "email": "string",
  "password": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|email|string|false|none|none|
|password|string|false|none|none|

<h2 id="tocS_AppointmentRequestDTO">AppointmentRequestDTO</h2>
<!-- backwards compatibility -->
<a id="schemaappointmentrequestdto"></a>
<a id="schema_AppointmentRequestDTO"></a>
<a id="tocSappointmentrequestdto"></a>
<a id="tocsappointmentrequestdto"></a>

```json
{
  "patientId": 0,
  "doctorId": 0,
  "date": "2019-08-24",
  "startTime": {
    "hour": 0,
    "minute": 0,
    "second": 0,
    "nano": 0
  },
  "reason": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|patientId|integer(int64)|false|none|none|
|doctorId|integer(int64)|false|none|none|
|date|string(date)|false|none|none|
|startTime|[LocalTime](#schemalocaltime)|false|none|none|
|reason|string|false|none|none|

<h2 id="tocS_LocalTime">LocalTime</h2>
<!-- backwards compatibility -->
<a id="schemalocaltime"></a>
<a id="schema_LocalTime"></a>
<a id="tocSlocaltime"></a>
<a id="tocslocaltime"></a>

```json
{
  "hour": 0,
  "minute": 0,
  "second": 0,
  "nano": 0
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|hour|integer(int32)|false|none|none|
|minute|integer(int32)|false|none|none|
|second|integer(int32)|false|none|none|
|nano|integer(int32)|false|none|none|

<h2 id="tocS_PatientDTO">PatientDTO</h2>
<!-- backwards compatibility -->
<a id="schemapatientdto"></a>
<a id="schema_PatientDTO"></a>
<a id="tocSpatientdto"></a>
<a id="tocspatientdto"></a>

```json
{
  "id": 0,
  "name": "string",
  "doctorId": 0,
  "doctorName": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer(int64)|false|none|none|
|name|string|false|none|none|
|doctorId|integer(int64)|false|none|none|
|doctorName|string|false|none|none|

<h2 id="tocS_DoctorDTO">DoctorDTO</h2>
<!-- backwards compatibility -->
<a id="schemadoctordto"></a>
<a id="schema_DoctorDTO"></a>
<a id="tocSdoctordto"></a>
<a id="tocsdoctordto"></a>

```json
{
  "doctorId": 0,
  "name": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|doctorId|integer(int64)|false|none|none|
|name|string|false|none|none|

<h2 id="tocS_PatientDataDTO">PatientDataDTO</h2>
<!-- backwards compatibility -->
<a id="schemapatientdatadto"></a>
<a id="schema_PatientDataDTO"></a>
<a id="tocSpatientdatadto"></a>
<a id="tocspatientdatadto"></a>

```json
{
  "id": 0,
  "name": "string",
  "dni": "string",
  "dateOfBirth": "2019-08-24",
  "gender": "M",
  "bloodGroup": "A",
  "rhFactor": "POSITIVE",
  "emergencyContact": "string",
  "doctorName": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer(int64)|false|none|none|
|name|string|false|none|none|
|dni|string|false|none|none|
|dateOfBirth|string(date)|false|none|none|
|gender|string|false|none|none|
|bloodGroup|string|false|none|none|
|rhFactor|string|false|none|none|
|emergencyContact|string|false|none|none|
|doctorName|string|false|none|none|

#### Enumerated Values

|Property|Value|
|---|---|
|gender|M|
|gender|F|
|gender|O|
|bloodGroup|A|
|bloodGroup|B|
|bloodGroup|AB|
|bloodGroup|O|
|rhFactor|POSITIVE|
|rhFactor|NEGATIVE|

<h2 id="tocS_MedicamentDTO">MedicamentDTO</h2>
<!-- backwards compatibility -->
<a id="schemamedicamentdto"></a>
<a id="schema_MedicamentDTO"></a>
<a id="tocSmedicamentdto"></a>
<a id="tocsmedicamentdto"></a>

```json
{
  "id": 0,
  "commonName": "string",
  "dosageForm": "string",
  "drugRoute": "string",
  "strength": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer(int64)|false|none|none|
|commonName|string|false|none|none|
|dosageForm|string|false|none|none|
|drugRoute|string|false|none|none|
|strength|string|false|none|none|

