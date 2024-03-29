# ssac3_test_android_todayhouse_miles

10/15

API가 없고 개발 가능시간도 짧았기 때문에 레이아웃 중심으로 구현하였다.

![initial](https://user-images.githubusercontent.com/66465380/137498974-3766f5fa-2d45-42f5-b1a7-ab6907b24912.JPG)

스플래시 화면은 별도의 액티비티를 이용하여 구현하였다. 
setTheme로 구현하는 것이 옳은 방식이라고는 하나 splash에서 로그인의 jwt토큰을 확인하기 위해 별도의 액티비티로 구현하는 것이 좋을 것이라고 판단하였다.


![initial](https://user-images.githubusercontent.com/66465380/137499538-cd568d52-3acf-4f2c-9a3a-321d792f6ff3.JPG)

로그인 화면을 구현하였다. 로그인버튼의 대부분은 이미지뷰로 구현하였는데 그 이유는 카카오톡 자체도 이미지를 제공해주기 때문에 이미지뷰를 클릭가능하게 하는 것이 
좋다고 생각하였다 SERVER프로그래머와 논의한 끝에 먼저 이메일을 이용한 자체 로그인 먼저 구현하는 것으로 하였다.


![initial](https://user-images.githubusercontent.com/66465380/137499587-99485448-4175-4404-a47e-0212e00ef6e2.JPG)

이메일 로그인을 구현하기 위해 레이아웃을 설계하였다. addTextChangedListener와  Pattern을 사용하여 Edit텍스트의 변경마다 원하는 값인지를 확인하였다.

![initial](https://user-images.githubusercontent.com/66465380/137592473-a173cc77-89c6-48ac-a4b5-23dc7e0c1055.png)

회원가입을 하기위한 레이아웃을 설계하고 클라이언트에서 가능한 코드들을 이용하여 API와 엮기 전 준비를 하였다.
TextWatcher를 이용하여 EditText의 변화가 있을때마다 유효성 검사가 가능 하도록하였다.

![initial](https://user-images.githubusercontent.com/66465380/137592608-1e2ccc60-2f9b-4e54-a221-2e731b794156.png)

회원 가입 후 최초화면에서 맞춤정보 설정을 위한 화면이 보이고 이를 구현하였다.
프로그레스바를 사용하여 현재 진행상황을 볼 수 있도록하였다. 이때 특이한 것이 하나의 스텝이 끝나면 다른 액티비티로 넘어가거나 새로운 탭이 추가되는 것이 아닌 하나의 스크롤에서 뷰가 밑으로 추가된다. 이를 구현하기 위해 ViewPager사용을 고려하였으나 어느 부분에서도 스크롤을 멈출 수 있다는 점에서 단순 NestedScrollView를 이용하여 Visibility를 변경하는 것으로 구현하는 것이 맞다고 판단했다.
다만 NestedScrollView하나의 하위 레이아웃만 가질 수 있기 때문에 3개의 뷰를 모두 화면크기에 맞추는 것은 불가능했고 이를 코드적으로 LayoutParams를 이용하여 해결하였다.

![initial](https://user-images.githubusercontent.com/66465380/137592630-28ee4b6c-c71b-4009-be75-c370d6e46bd4.png)

본격적인 앱화면 구성을 시작하였다.
앱이 복잡한 계층구조를 이루는 것 같아 이를 해석하는데 시간이 좀 걸렸다.
먼저 최 상위에 바텀네비게이션을 가진 메인 액티비티 - 탭 레이아웃을 가지는 스토어 프레그먼트 - 홈탭에 표시되는 스토어 홈프레그먼트를 구현하였다.
이때 탭레이아웃의 툴바가 아래 스크롤시 보이지 않고 위 스크롤시 보이게 되는데 이를 위해 CoordinatorLayout의 scrollFlags를 사용하였다.

10-17 

오늘은 로그인 관련 API를 구현하는 것을 우선 순위로 삼고 작업을 진행하였다.
![initial](https://user-images.githubusercontent.com/66465380/137632125-55d1abde-ec05-40a1-af10-1cce2e8a05ad.png)

먼저 로그인 화면을 구현 서버에서 아이디와 비밀번호를 대조한 후, 정상일 시 로그인이 가능하도록 jwt토큰을 받아 저장하였다.
이 후, 앱을 다시 실행할 때, 저장된 jwt 토큰이 존재한다면 스플래시 액티비티에서 바로 메인 액티비티, 아니라면 로그인 액티비티로 이동이 가능하도록 하였다.

회원가입 액티비티에서는 post를 통해 새로운 회원이 가입될 수 있도록 하였으며 정상적인 가입이 완료되었을 때 바로 로그인이 실행되도록 하였다.

![initial](https://user-images.githubusercontent.com/66465380/137632103-d2ac4192-45a8-48f8-8aa9-d622aa14f77a.png)

그리드 뷰를 통해 기본적인 카테고리가 보이도록 하였다. 

오늘은 예상외로 적은 분량밖에 구현하지 못했다...
밀린 분량은 내일 더 분발하여야 할 것 같다.

10/18

오늘은 카테고리별 검색 그 중에 검색에 대한 부분을 구현하려고 하였다.

![initial](https://user-images.githubusercontent.com/66465380/137744078-d1db041a-40bb-4908-bfb0-b6c2ed91e5a4.png)

먼저 드로어 레이아웃을 이용하여 상세필터를 만들었다. 이때 현재 카테고리를 감지해서 정확한 필터 조건들이 들어갈 수 있도록 하였다.

![initial](https://user-images.githubusercontent.com/66465380/137744144-dcc60e71-00bd-426a-a918-ee7cdce82f98.png)

또한 액티비티 부분에 상세필터를 호출하는 버튼과 몇몇 필터의 정보를 표시해주는 레이아웃을 만들었고
두 필터 모두 클릭시 선택한 정보가 플레그먼트에 넘어갈 수 있도록 하였다.
하지만 두 필터를 연결하려는 시도에서 실패하였으며 데드라인을 내일까지 설정하여 작업을 진행할 예정이다.
상품 페이지(전체)
10/18 11:00 AM( or ~10/19 12:00PM)
상품 페이지(하위 카테고리별)
10/20 11:00 AM( or ~10/21 12:00PM)

기존에 설정한 데드라인을 맞추지 못했다... 
최대한 빨리 작업을 진행해야 할 것 같다...

10/15

필터간의 연동을 가능하게 했다.

최대한 빠른 작업을 하려했으나 수업이 많은 날이라 가용시간을 6시간 정도 밖에 사용하지 못했다.
API중 카테고리별 검색 API를 추가로 적용하였으며 전체 카테고리 검색 API를 다듬었다.

![initial](https://user-images.githubusercontent.com/66465380/137942176-b3548c37-20fd-4862-bb75-75671e1289ef.png)
![initial](https://user-images.githubusercontent.com/66465380/137942251-01fbe3c1-2013-4316-b717-e8bb0d1b0ea2.png)

다음과 같이 서버에서 아이템 정보를 받아오는 것에 성공하였다.
정말 다양한 필터가 있지만 여건 상 패브릭의 경우 색상과 리퍼상품여부, 패턴만을 검색 가능하게 하였다.

사진의 경우 충분한 준비가 되어있지않아 일단은 비워두었으며 추가로 글라이드를 엮을것이다. 

내일의 경우 전체 카테고리 페이지와 필터 카테고리 페이지를 완성에 가깝게 구현하고 
추가로 상세페이지 API를 엮을 예정이다.

10/20~10/21

![initial](https://user-images.githubusercontent.com/66465380/138297213-aa1cf067-13e6-40c6-b064-4fdb89a6dff3.png)

필터를 완성시켰다. 필터의 내용은 선택된 카테고리에 따라 동적으로 변할 수 있도록 하였으며 하나의 오브젝트를 드로어의 필터, 칩그룹의 필터, 리사이클러뷰의 필터가 공유하는 것으로
모든 필터가 연동되게 하였다. 
필터를 누르게 되면 인터페이스를 통해 프레그먼트의 필터 정보 부르기 메서드가 호출되고 현재 필터 정보를 얻은 후 요청에 맞추어 서버에 요청을 보내 응답을 받는다. 이를 이용해서 필터의 값이 변할 때마다 항상 서버에서 해당하는 정보가 불러 올 수 있게 하였다.

![initial](https://user-images.githubusercontent.com/66465380/138297788-815b8530-af45-41bd-a97e-59fca749b58f.png)

홈 플레그먼트를 구축하였다. 카테고리와 관련 없이 모든 아이템의 정보를 서버에서 얻어와 뿌려주었는데 이때 선단 4개의 아이템을 오늘의 딜로 선정, 내부에서 타이머를 이용, 랜덤하게 주어진 남은 시간이 1초마다 줄어들어 화면에 표시될 수 있도록 하였다.

![initial](https://user-images.githubusercontent.com/66465380/138297906-2f4b9a3d-4ffa-48dc-bb41-3c7beeaf9e6f.png)

최근 본 상품의 경우 최대 8개의 아이템ID를 로컬에 저장해 두었다가 해당하는 아이템의 정보만 얻어와 뿌려주게 하였다.
인기상품의 경우 현재는 모든 아이템을 뿌려주게 만들었다.

![initial](https://user-images.githubusercontent.com/66465380/138307164-c0b40d59-8f11-46e4-8dfc-8cb77bd3cf0f.png)

![initial](https://user-images.githubusercontent.com/66465380/138307015-2cfc08ce-b30f-4422-a13e-d881fb002e88.png)

상세화면 레이아웃을 만드는 것에 성공하였고 상세화면 API와의 연동 또한 성공하였다. 이제 이 둘을 연결만 시켜주면 된다.
내일은 상세페이지 완전 구현 리뷰 페이지 구현과 리뷰등록 API연동을 완료할 생각이다.
이것이 완료되면 사진과 관련된 기능을 구현할 예정이다.

10/22 ~ 10/23
![initial](https://user-images.githubusercontent.com/66465380/138565965-821263ba-dc8d-4e5b-8ad6-2e7be797208e.png)
![initial](https://user-images.githubusercontent.com/66465380/138565977-def205c1-fe09-41ae-ade2-5483c972e959.png)
![initial](https://user-images.githubusercontent.com/66465380/138565986-2404dc89-0bec-43e8-899a-4ff2c2bde44e.png)

아이템 클릭시 상세정보가 보이는 액티비티를 구현하였다.
레트로핏을 통해 받아온 모든 정보를 화면에 뿌려주며 list형태로 리뷰도 얻어와 각 점수 구간의 비율을 계산 프로그레스바에 표시될 수 있게 하였다.
또한 비슷한상품의 경우 같은 카테고리의 아이템 4개를 보여주도록 하였다.

![initial](https://user-images.githubusercontent.com/66465380/138565998-8c2071fc-7bff-4c7e-a083-44341d91723d.png)

리뷰를 등록하는 화면을 만들었다. 또한 서버의 리뷰등록API와 연동하여 실제 서버에 리뷰가 올라갈 수 있도록 하였다. 
이때 이미지의 경우 서버쪽에 이슈가 있어 FireBaseStorage를 연결한 후 이미지 자체는 FireBaseStorage에 저장, 그후 저장된 url을 서버에 넘겨주고, 넘겨받아 glide로 띄워줄 수 있도록 하였다.


![initial](https://user-images.githubusercontent.com/66465380/138566016-bbfce4c2-5b02-49db-bf3a-4fcff857c55d.png)

기본화면 레이아웃을 수정해 디테일을 좀 더 얻고 SharedPreferences에 탐색한 아이템의 id를 8개까지 gson을 통해 저장한다. 
후에 저장한 아이템 id를 이용하여 최근 본 상품을 실제로 볼 수 있게 하였다.

![initial](https://user-images.githubusercontent.com/66465380/138566217-273a11fb-ef3b-40c4-b18b-6e57c9ae482d.png)

또한 필터를 통해 아이템을 탐색할 때, 페이징을 통해 2개씩 불러오도록 하였다.(2개인 이유: 저장된 아이템의 수가 적기때문에)

![initial](https://user-images.githubusercontent.com/66465380/138566032-4c60587c-60e8-48aa-9f26-8462287abdbd.png)
![initial](https://user-images.githubusercontent.com/66465380/138566058-9a3bb308-8bfa-48d8-950d-cad82dd92d3a.png)

홈화면을 구현하였다. 레이아웃을 중심으로 진행했으며 이제 사진을 올리는 핵심기능을 구현할 예정이다.


10/24

사진을 올리는 핵심기능을 구현하려고 노력하였다.

![initial](https://user-images.githubusercontent.com/66465380/138605413-1beee65a-e98d-44d7-aa43-328e63f12e65.png)

먼저 사진을 등록하는 화면의 레이아웃을 만들었다.

사진을 누르게 되면 해당사진에 태그를 추가할 수 있는 레이아웃으로 넘어가게 된다. 

![initial](https://user-images.githubusercontent.com/66465380/138605364-360cca9b-857b-46ac-8e54-46ef90fe2447.png)
![initial](https://user-images.githubusercontent.com/66465380/138605388-7cd92396-7040-4563-9086-5bfb90bcfce0.png)

해당 레이아웃에서 사진을 누르게 되면 topsheet레이아웃을 보여준다. 해당 레이아웃은 서버와 연결되어 이름으로 등록된 아이템을 찾을 수 있다. 해당 아이템을 찾고나면 등록 버튼을 누르게 되는데
이를 누르게 되면 해당 위치에 해당 아이템의 정보가 기록된 태그가 보여지게 된다.

10/25

오늘은 총 4개의 수정사항을 피드백 받았다.
1. 레이아웃 다듬기
2. 사진 등록 보기 API 기능 구현
3. 장바구니 구현
4. 내 리뷰보기 API구현


먼저 4번을 완료하였다.

![initial](https://user-images.githubusercontent.com/66465380/138748091-ae114b90-5bbf-450f-83aa-9243cea9b515.png)
![initial](https://user-images.githubusercontent.com/66465380/138748132-fc0d4237-d255-425d-b69c-52a35dbb599e.png)

내 리뷰액티비티로 넘어가기 위해 간단한 프레그먼트 레이아웃을 만들었다.

![initial](https://user-images.githubusercontent.com/66465380/138748187-ff4c7578-fd53-413e-8cb7-91d39204d34c.png)

여기서 내 리뷰액티비티로 넘어갈 수 있고 API를 연동 내 리뷰만 보는 기능을 구현하였다.

다음으로 2번을 70%정도 구현 완료하였다.
사진에 아이템의 정보를 태그로 달아 등록하는 API를 연결하였으며 이를 전체받기 API, 상세받기API를 연결하였다.
또한 이를 위해 홈프레그먼트의 레이아웃을 꾸몄고 상세페이지의 레이아웃또한 꾸몄다.
API의 연결을 확인하였고 이를 내일 화면에 뿌려줄 예정이다.

![initial](https://user-images.githubusercontent.com/66465380/138747792-d8bcee7d-6a59-47c8-a12d-ebf543af1e08.png)
![initial](https://user-images.githubusercontent.com/66465380/138747956-22baaad1-a328-4cd9-ab17-912bcc0cf515.png)

![initial](https://user-images.githubusercontent.com/66465380/138748037-fe567ff6-fc9d-49f0-a6f7-322ef19f81b5.png)

이제 내일은 화면에 사진정보를 뿌려주는 작업을 완료하고. 바텀다이얼로그프레그먼트를 이용 장바구니를 구현할 예정이다.
그리고 수요일은 구현못한 기능이 있다면 이를 구현하고 레이아웃을 다듬는 작업을 할 것이다.


10/26

![initial](https://user-images.githubusercontent.com/66465380/138931388-365f2d28-870b-4c00-b71d-25f3e27bec81.png)
![initial](https://user-images.githubusercontent.com/66465380/138931471-d9a2b74b-f6a3-44d7-b01f-7740d81f8cce.png)

사진 상세보기 API의 구현을 완료하였다. 이것으로 원하던 핵심기능을 미숙하게 나마 구현하였다.
사진의 담긴 아이템의 정보로 원하는 상품을 찾을 수 있다. 비슷한 아이템 추천기능은 없기 때문에 간단하게 모든 사진을 뿌려주었다.


![initial](https://user-images.githubusercontent.com/66465380/138931532-c56e4db1-b0c2-4341-837d-9518ad0be4f1.png)

장바구니 담기부분의 API를 연결하였다. 다만 스피너를 커스텀 하는데 많은 시간이 걸릴 것으로 예상 우선순위를 뒤로 미루고 정해진 데이터를 장바구니로 입력하게 하였고 이를 성공하였다.

![initial](https://user-images.githubusercontent.com/66465380/138931667-a230b1c9-18f7-487f-b14d-02bedda4a8c8.png)
![initial](https://user-images.githubusercontent.com/66465380/138931743-467ffa7d-5cba-481a-8bb3-fb60c8ae1b3c.png)

장바구니 불러오기 API를 연결하고 싶었으나 API구현에 문제점이 있어 협의후 내일 해결하는 것으로 하였다.

마찬가지로 전체리뷰 API도 문제가 있었기 때문에 레이아웃을 선구현 내일 연동하는 것으로 하였다.

![initial](https://user-images.githubusercontent.com/66465380/138931814-732ef630-d5b5-47f7-a4bd-4835107f613a.png)
![initial](https://user-images.githubusercontent.com/66465380/138931925-4b9a493d-8670-41f2-a7a9-7443cdd302df.png)

내일은 2가지 API를 연동하고 미숙한 레이아웃 부분을 모두 다듬을 생각이다.
해당 사항이 완료되면 2주간의 라이징테스트가 완료된다.

10/27
장바구니와 전체리뷰를 구현하고 레이아웃을 다듬었다.
이번 과제를 진행하며 많은 것을 느꼈는데 먼저 설계가 중요하다는 것이다. 2주의 짧은 과정동안 원하는 목표를 구현하기 위해 설계를 충분히 진행하지 못했다.
이는 후반부에 치명적인 문제로 각 기능간의 연결에서 많은 오류가 발생하였고 이를 수정하는데 시간이 좀 오래걸렸다.
또한 소통 역시 매우 중요하다고 느꼈는데 원하는 API를 정확한 형태로 받기위해 정말 많은 대화를 나누었고, 혼자 잘하는 것보다 상대를 이해하며
협업하는 것이 중요하다고 깨달았다.

후반부에는 API를 엮는 것에만 주력하여 예외처리나 디테일을 챙기지 못한 것이 아쉽다.
하지만 실력은 정말 향상되고 있는 것이 몸으로 느껴져 보람은 있었다.
이를 발판으로 더 좋은 개발자가 되기 위한 여러 공부와 소통능력을 향상시키는 것을 목표로 해야겠다.
