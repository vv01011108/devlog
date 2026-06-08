# DevLog Backend

개발 기록을 작성하고 관리할 수 있는 DevLog 서비스의 백엔드 서버<p/>
Spring Boot 기반으로 JWT 인증, 게시글 CRUD, MySQL 연동, AWS EC2 배포, GitHub Actions 자동배포를 구현

<hr/>

### ⚙️ 주요 기능

- 회원가입, 로그인 / JWT 인증
- 게시글 작성
- 게시글 목록 조회
- 게시글 상세 조회
- 게시글 수정
- 게시글 삭제
- 작성자 본인만 수정 / 삭제 가능
- GitHub Actions 기반 EC2 자동 배포


### 🔨 기술 스택

#### Backend
- Java 21
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- MySQL
- Gradle


#### Infrastructure
- AWS EC2
- AWS RDS MySQL
- Nginx
- GitHub Actions


### 🏢 아키텍처

```text
React Frontend
  ↓
S3 Static Website Hosting
  ↓
EC2 Nginx Reverse Proxy
  ↓
Spring Boot Application
  ↓
RDS MySQL
```


### ☁️ 배포 단계
1. 로컬 빌드: 로컬 환경에서 Spring Boot 프로젝트를 빌드하여 JAR 파일을 생성
2. JAR 파일 업로드: EC2 인스턴스로 업로드
3. 환경 변수 설정: devlog.env 파일에 DB 연결 등 필요한 환경 변수 구성
4. 시스템 서비스 등록: systemd 서비스로 등록하여 백그라운드 실행, 자동 재시작 관리
5. DB 연결: 보안 그룹 설정을 통해 EC2 인스턴스에서 RDS로의 접근 허용
6. 데이터 검증: DataGrip의 SSH 터널링 기능을 사용해 RDS에 안정하게 접속하고 데이터를 확인
