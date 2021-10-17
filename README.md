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
