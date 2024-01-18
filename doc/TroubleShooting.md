# Trouble Shooting

#### 오류 내용

'Description:

Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

Reason: Failed to determine a suitable driver class'
<br/><br/><br/>
user-api의 domain.model을 domain모델로 옮기는 리펙토링 과정에서 발생하였음

원인은 yml에 Database에 연결할 때 필요한 정보가 없기 때문이다<br/><br/>

#### 해결 방안
기존의 application.yml에 profiles.include에 domain을 추가하고, 기존에 작성한 코드는 application-domain으로 옮겼음

***
#### 오류 내용

'java.lang.ClassNotFoundException: javax.xml.bind.JAXBException'

#### 해결 방안
Jwt를 사용하면서 발생한 문제로,
gradle에 아래와 같은 dependency를 추가하여 해결 하였음

```
implementation 'javax.xml.bind:jaxb-api:2.3.0'
```

***

#### 오류 내용

'OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended'

#### 해결 방안
찾아보니 오류가 아니라 경고 메시지였고 JDK 1.8버전 이후에 추가된 기능으로, 클래스 로딩 시 Class Data Sharing(CDS)기능을 사용하면 발생한다고 한다.

해결 방법

File > Settings > Build, Exception, Deployment > Debugger > Async Stack Traces

***