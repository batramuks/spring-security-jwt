
1. **Request for POST /autenticate:**

POST http://localhost:8080/authenticate

Request: 
{
    "username" : "admin",
    "password" : "admin123"
}

Response:
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc5NTQzMzkzOCwiZXhwIjoxNzk1NDM3NTM4fQ.Xp0O-14twa9wlrVeWrSV59fwDgXLknCRNvpiDP55Rz8

2. **Use the above token in other APIs call as Bearer token**

  Using postman:
  
  AuthType: Bearer Token
  Token Value: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc5NTQzMzkzOCwiZXhwIjoxNzk1NDM3NTM4fQ.Xp0O-14twa9wlrVeWrSV59fwDgXLknCRNvpiDP55Rz8
  
  GET http://localhost:8080/applications/a1f276bb-6301-46c3-89c5-d92d19283d74
  
  Response:
  
  {
    "appId": "APP-10482",
    "appStartTime": null,
    "appEndTime": null,
    "applicationStatus": "PENDING",
    "processState": "INITIAL",
    "cmndProcessDefName": "sample-workflow",
    "cmndProcessDefVersion": null,
    "apiVersion": "v1",
    "countryCode": "US",
    "currencyCode": "USD",
    "createdBy": "Alex Morgan",
    "createdOn": "2026-11-23T12:44:31.2174374",
    "modifiedBy": null,
    "modifiedOn": "2026-11-23T12:44:31.2174374",
    "cspTimestamp": "2026-11-23T12:44:31.2174374",
    "manualScoringNeeded": 1
}

