# ssac3_test_android_todayhouse_miles

10/15

API가 없고 개발 가능시간도 짧았기 때문에 레이아웃 중심으로 구현하였다.

<img src="https://github.com/mock-ssac3/ssac3_test_android_todayhouse_miles/issues/1#issue-1027471851">
스플래시 화면은 별도의 액티비티를 이용하여 구현하였다. 
setTheme로 구현하는 것이 옳은 방식이라고는 하나 splash에서 로그인의 jwt토큰을 확인하기 위해 별도의 액티비티로 구현하는 것이 좋을 것이라고 판단하였다.


<img src="https://github.com/mock-ssac3/ssac3_test_android_todayhouse_miles/issues/3#issue-1027475315">
로그인 화면을 구현하였다. 로그인버튼의 대부분은 이미지뷰로 구현하였는데 그 이유는 카카오톡 자체도 이미지를 제공해주기 때문에 이미지뷰를 클릭가능하게 하는 것이 
좋다고 생각하였다 SERVER프로그래머와 논의한 끝에 먼저 이메일을 이용한 자체 로그인 먼저 구현하는 것으로 하였다.


<img src="https://github.com/mock-ssac3/ssac3_test_android_todayhouse_miles/issues/3#issue-1027475315">
이메일 로그인을 구현하기 위해 레이아웃을 설계하였다. addTextChangedListener와  Pattern을 사용하여 Edit텍스트의 변경마다 원하는 값인지를 확인하였디
