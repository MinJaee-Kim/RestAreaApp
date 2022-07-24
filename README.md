# RestAreaApp
휴게소_앱
=============
한국도로공사_노선별, 방향별 휴게소 편의시설 현황<br>
https://www.data.go.kr/data/15076640/openapi.do<br>
한국도로공사_휴게소 푸드메뉴현황 조회 서비스<br>
https://www.data.go.kr/data/15076643/openapi.do<br>
카카오 키워드 검색<br>
https://developers.kakao.com/docs/latest/ko/local/dev-guide<br>
네이버 direction5<br>
https://api.ncloud-docs.com/docs/ai-naver-mapsdirections-driving<br>
등의 OPEN API를 사용한 앱입니다.<br>


메인화면
-------------
![휴게소1](https://user-images.githubusercontent.com/75728238/180640334-c505b0bb-2ca1-4e32-b66e-4b843d38166b.PNG)
<br>
<br>
클라이언트 - 안드로이드와 서버 - 오라클 클라우드를 사용하였으며 <br>
MariaDB를 이용하여 데이터를 저장하였고 php를 사용하여 통신하였습니다.

검색화면
-------------
![휴게소2](https://user-images.githubusercontent.com/75728238/180640335-b401300c-8bf8-4b9e-9236-42b29da4e4fc.PNG)
<br>
<br>
작동 화면이며 메인에 지도가 있고 핀을 눌렀을 때 해당 게시글을 볼 수 있습니다.

검색시
-------------
![휴게소3](https://user-images.githubusercontent.com/75728238/180640336-ecb96c2c-29ba-4c51-9c62-d1d07f9f1a29.PNG)
<br>
<br>
로그인 화면이며 php에서 JWT기술을 사용하였고<br>
남아있는 토큰의 값으로 자동 로그인을 구현하였습니다.


결과화면
-------------
![휴게소4](https://user-images.githubusercontent.com/75728238/180640337-5beb32ae-5bca-490b-a145-ad791aacfc24.PNG)
<br>
<br>
메인 화면이며 핀을 눌렀을때 바텀 시트가 작동합니다 <br>
검색은 카카오에서 제공하는 API를 이용하였으며 쿼리를 던지면<br>
Json데이터를 받아옵니다.

탐색화면
-------------
![휴게소5](https://user-images.githubusercontent.com/75728238/180640339-9f4414c2-742f-41d6-abef-0c0bac7b9cca.PNG)
<br>
<br>
SNS 처럼 볼 수 있게 만들었으며 상단의 버튼을 눌러 지도의 해당 위치로 이동할 수 있고<br>
좋아요를 남길 수 있으며, 댓글 버튼을 이용해 댓글과<br>
프로필을 눌렀을때 해당 유저가 쓴 글만 볼 수 있습니다<br>


탐색화면 세부
-------------
![휴게소6](https://user-images.githubusercontent.com/75728238/180640340-eec28fa2-dfd7-412d-ad89-45173e9d7a08.PNG)
<br>
<br>
게시글 작성화면이며 맵버튼을 이용해 내 위치를 남겨서 게시글을 작성 할 수 있고<br>
작성 버튼을 이용하여 일반적인 글을 쓸 수도 있습니다. 이는 레트로핏을 사용하였습니다.
