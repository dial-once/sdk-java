# dialonce-sdk-java

This git repository contains the Dial Once Java SDK composer package.

Download
--------

Grab via Maven:
```xml
<dependency>
  <groupId>com.dialonce.sdk</groupId>
  <artifactId>dialonce-sdk-java</artifactId>
  <version>0.1.0</version>
</dependency>
```

# Usage

## IVR
For all of the IVR exposed method usage, you will need to instantiate an Application object using your api key and secret:

```java
Application app = new com.dialonce.sdk.Application('API_KEY', 'API_SECRET');
```

The code above will generate a token by calling our API. If your script is not persistent in memory or if you don't have APC, you may want to use a previously fetched token directly:
```java
Application app = new com.dialonce.sdk.Application('API_TOKEN');
```

### Log a call step to Dial Once
To allow us to gather analytics, and provide you some important KPI info, we need to get some call steps from your IVR:

```java

IVR ivr = new com.dialonce.sdk.IVR(Application application, String callerNumber, String calledNumber);

ivr.log(IVR.LogType.CALL_START);
ivr.log(IVR.LogType.CALL_END);
ivr.log(IVR.LogType.ANSWER_GET_SMS);
//etc.
```

`application`: a `com.dialonce.sdk.Application` object instance  
`callerNumber`: a String, the caller phone number (inter. format with leading +)  
`calledNumber`: a String, the IVR phone number that the user called (inter. format with leading +)

### Check if a caller is eligible to use the Dial Once service

```java
IVR ivr = new com.dialonce.sdk.IVR(Application application, String callerNumber, String calledNumber);
if ( ivr.isEligible() ) {

}
```
`application`: a `com.dialonce.sdk.Application` object instance  
`callerNumber`: a String, the caller phone number (inter. format with leading +)  
`calledNumber`: a String, the IVR phone number that the user called (inter. format with leading +)

### The user requested the Dial Once service

```java
IVR ivr = new com.dialonce.sdk.IVR(Application application, String callerNumber, String calledNumber);
ivr.serviceRequest();
```
`application`: a `com.dialonce.sdk.Application` object instance  
`callerNumber`: a String, the caller phone number (inter. format with leading +)  
`calledNumber`: a String, the IVR phone number that the user called (inter. format with leading +)

### Check if the caller uses a mobile phone

```java
IVR ivr = new com.dialonce.sdk.IVR(Application application, String callerNumber, String calledNumber);
if (ivr.isMobile()) {

}
```
`application`: a `com.dialonce.sdk.Application` object instance  
`callerNumber`: a String, the caller phone number (inter. format with leading +)  
`calledNumber`: a String, the IVR phone number that the user called (inter. format with leading +)