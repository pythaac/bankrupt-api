# bankrupt
[대한민국 법원 회생/파산 자산매각 공고게시판](https://www.scourt.go.kr/portal/notice/realestate/RealNoticeList.work?pageIndex=1&searchWord=&bub_cd=) 연동 API 서버

## Requirements
자산매각 공고게시판 자료를 제공하는 API로 데이터는 대한민국 법원에서 제공하는 데이터와 일치하지만,  
다음과 같은 요구사항 요청을 받아 제작하게 되었음

#### 첨부 파일을 바로 볼 수 있으면 좋겠다
- 게시판에서 게시물로 들어가야 파일을 볼 수 있음 (게시판 -> 게시물 -> 파일 다운로드)
- 모든 파일이 다운로드를 받은 후에 열람이 가능함

#### 카테고리별로 공고를 볼 수 있으면 좋겠다
- 공고게시판은 매각 자산 유형으로 나누어 공고를 볼 수 없음
- 공고게시판은 매각하는 자산이 무엇인지 알기 어려움 (ex. 제목 : 자산 매각공고)
- 매각 자산의 유형을 확인하게 위해 첨부 파일을 열람해봐야 함

## Feature
위 요구사항을 위해 해당 서비스는 아래와 같은 기능을 제공
1. 게시판에서 바로 파일에 접근 가능 (게시판 -> 파일 다운로드)
2. 파일 다운로드 없이 브라우저 뷰어로 열람 가능 (PDF만 제공)
3. Category/Keyword 기능 제공 (추가/삭제 가능)

#### Category  
게시물이 속하는 범주

#### Keyword  
게시물의 제목 또는 첨부파일에 Category의 keyword가 포함될 경우, 해당 Category로 분류

예를 들면, "자동차"라는 Category가 있고, "자동차" Category에 `버스`, `트럭`, `포크레인` keyword가 있다고 할 때,  
게시물 A의 제목 또는 첨부파일에 `버스` 또는 `트럭` 또는 `포크레인`이 포함될 경우, A의 Category에 "자동차"가 추가된다.
```
자동차 (Category)
- 버스 (Keyword)
- 트럭 (Keyword)
- 포크레인 (Keyword)

버스, 트럭, 포크레인 중 하나만 포함 -> 자동차 카테고리
```

## Synchronization
- 데이터는 주기적으로 공고게시판과 동기화한다. 트래픽을 고려하여 **1시간마다 동기화**하도록 schedule되어있다.
- Category의 keyword를 **추가/삭제할 때마다** 해당 Category에 대해 업데이트 한다. 모든 게시물 및 첨부파일을 확인하므로 시간이 소요된다.
