# Trouble Shooting

#### 오류 내용

'OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended'

#### 해결 방안
찾아보니 오류가 아니라 경고 메시지였고 JDK 1.8버전 이후에 추가된 기능으로, 클래스 로딩 시 Class Data Sharing(CDS)기능을 사용하면 발생한다고 한다.

해결 방법

File > Settings > Build, Exception, Deployment > Debugger > Async Stack Traces

***