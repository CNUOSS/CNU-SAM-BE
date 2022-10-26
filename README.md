# 2022 Graduation Project

## 프로젝트 주제
오픈소스SW 관리 도구 FOSSLight를 기반으로 한 학내 자산 관리 도구 개발

<br>

## 추진 배경
- SW 관리 부재로 발생하는 법률적 문제 발생

- 체계적이지 않은 학내 SW 자산 관리 시스템 

- 소프트웨어 공정 문화의 결핍

- 관리되지 않고 있는 학교 구성원이 개발한 SW

<br>

## 목표 및 주요 기능
학내에서 사용되는 다양한 SW를 관리(등록 및 조회) 할 수 있는 SW 자산관리 프로덕트를 구축

- SW는 학교에서 구독하고 있는 공용 SW를 관리

- 학교 수업에서 사용하는 SW를 관리

- 학교 구성원이 개발한 SW를 관리

- 학교 구성원이 개발한 SW에 사용된 라이선스와 이에 따른 규제들을 안내

<br>

## 활용 방안 및 기대 효과
- SW를 등록하고, 다양한 정렬과 시각화 방식으로 SW를 조회함으로써 학교에서 다루고 있는 SW를 효율적으로 관리가 가능
  
  - 수기로 작성하며 데이터를 하나씩 취합하는 관리 과정에서, CNU-SAM을 통해 간단하게 데이터를 등록, 수정, 삭제, 조회 가능
  
  - 사용 목적에 따른 다양한 시각화 기능들을 통해 추가적인 과정 없이 2차 가공한 데이터를 확인 가능
  
  - 수업용 sw 관리를 통해, 불법 sw 사용으로 인한 법적 문제를 사전에 파악하고 예방

- 공용 SW를 체계적으로 관리함으로 예산 범위에서 효율적으로 학내 구성원들의 소프트웨어 수요를 충족 가능
  - 사용 여부와 사용량 파악을 통해, 추후 공용 SW 구매에 참고하여 효율적인 SW 사용이 가능

- 학내 개발자는 올바른 방식으로 오픈소스를 사용할 수 있으며, 소프트웨어 공정 이용 문화가 정착

<br>

## 프로젝트 구조
![pj](https://github.com/CNUOSS/.github/blob/master/profile/img/pj.png)

<br>

## 개발 환경
- **Frontend**
    - TypeScript : 4.6.2
    - React : 17.0.2
- **Backend**
    - Java : 17
    - Spring Boot : 2.6.6
    - MySQL : 8.0.29
- **Server**
    - linux : Ubuntu 18.04

<br>

## 개발 과정

|DESIGN|FRONT|BACK|
|:---:|:---:|:---:|
|[FIGMA](https://www.figma.com/file/eqApqHEmv1BH3dX3jT8Opa/CNU_SAM?node-id=0%3A1)|[STORYBOOK](https://62679f79619a13004a8e5780-wtrwczgjor.chromatic.com/?path=/story/container-addorupdatelectureswtab--create-tab)|[API](https://www.notion.so/API-18cb53dfac584db889b6cd116aaf12c7), [DATABASE](https://www.notion.so/86345d650a7341fc9b125fc33fb8d908)|

<br>

## (중간) 결과물

### 프로젝트 결과
#### Docker로 설치

`docker pull mysql`

`docker pull redis`

`docker pull fortune00/cnusam`
 
 #### Docker로 실행
 
 `docker run fortune00/cnusam`

#### 직접 파일 실행

`docker run -p 3360:3306 -e MYSQL_ROOT_PASSWORD=root mysql`
 
`docker run -p 6379:6379 redis`
  
`./gradlew clean build -x test`

`java -Dspring.profiles.active=local -jar ./build/libes/CNU-SAM-BE-0.0.1-SNAPSHOT.jar`

### [발표 자료](https://docs.google.com/presentation/d/1Y0q7jJ7Z9ecbyBmio7GP2LWS1yq0IYEHojlePhpeQhI/edit#slide=id.g12f65192fc9_0_25)

### [데모 영상](https://www.youtube.com/watch?v=G3NfMfse_ik)

## Team Member
|이름|아이디|역할|
|:---:|:---:|:---:|
|🦙 박은식|shellboy|팀장 & 프론트 개발|
|🐥 문정현|MoonDD|백엔드 개발|
|🦕 복신영|fortune00|백엔드 개발 & 디자인|


