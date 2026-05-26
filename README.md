# DevLog Deployment Architecture
### AWS 실습 프로젝트

Client<br>
⬇️
EC2 Public IP: 8080<br>
⬇️
Spring Boot Application<br>
⬇️ 
RDS MySQL

### 배포 단계
1. 로컬 빌드: 로컬 환경에서 Spring Boot 프로젝트를 빌드하여 JAR 파일을 생성
2. JAR 파일 업로드: EC2 인스턴스로 업로드
3. 환경 변수 설정: devlog.env 파일에 DB 연결 등 필요한 환경 변수 구성
4. 시스템 서비스 등록: systemd 서비스로 등록하여 백그라운드 실행, 자동 재시작 관리
5. DB 연결: 보안 그룹 설정을 통해 EC2 인스턴스에서 RDS로의 접근 허용
6. 데이터 검증: DataGrip의 SSH 터널링 기능을 사용해 RDS에 안정하게 접속하고 데이터를 확인
