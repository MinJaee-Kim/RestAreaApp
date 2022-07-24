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
MVVM패턴을 사용하여 제작해 보았습니다<br>


메인화면
-------------
![휴게소1](https://user-images.githubusercontent.com/75728238/180640334-c505b0bb-2ca1-4e32-b66e-4b843d38166b.PNG)
<br>
<br>
메인화면의 모습입니다. 네이버 지도 화면이며 Nav-Graph를 활용하여 제작해 보았습니다.

검색화면
-------------
![휴게소2](https://user-images.githubusercontent.com/75728238/180640335-b401300c-8bf8-4b9e-9236-42b29da4e4fc.PNG)
<br>
<br>
검색화면입 상단 장소 검색 버튼을 클릭하여 들어올 수 있습니다.

검색시
-------------
![휴게소3](https://user-images.githubusercontent.com/75728238/180640336-ecb96c2c-29ba-4c51-9c62-d1d07f9f1a29.PNG)
<br>
<br>
상단의 글자가 바뀔때 마다 retrofit요청을 받아오며 kakao키워드 검색을 활용하여 제작하였습니다.


결과화면
-------------
![휴게소4](https://user-images.githubusercontent.com/75728238/180640337-5beb32ae-5bca-490b-a145-ad791aacfc24.PNG)
<br>
<br>
결과화면입니다. 네이버 길찾기 direction5를 활용하여 제작하였으며 특정 구간마다 카카오 keyword검색을 사용하여<br>
구간별로 근처의 휴게소를 탐색하며 탐색된 휴게소를 저장하여 뿌립니다. <br>
이 과정에서 코루틴을 사용하였습니다.<br>

탐색화면
-------------
![휴게소5](https://user-images.githubusercontent.com/75728238/180640339-9f4414c2-742f-41d6-abef-0c0bac7b9cca.PNG)
<br>
<br>
한국도로공사_노선별, 방향별 휴게소 편의시설 현황<br>
https://www.data.go.kr/data/15076640/openapi.do<br>
구간별로 검색된 휴게소들의 정보를 검색하여 해당 휴게소가 제공하는 편의시설과, 근처 유료시설을 알려줍니다.


탐색화면 세부
-------------
![휴게소6](https://user-images.githubusercontent.com/75728238/180640340-eec28fa2-dfd7-412d-ad89-45173e9d7a08.PNG)
<br>
<br>
한국도로공사_휴게소 푸드메뉴현황 조회 서비스<br>
https://www.data.go.kr/data/15076643/openapi.do<br>
해당 휴게소의 푸트코트의 음식을 알려줍니다.
