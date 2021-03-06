let addr_btn = document.querySelector('#addr_btn'); 				// 주소 변경 버튼
let addr_before = document.querySelector('#addr_before'); 			// 현재 주소
let addr_after = document.querySelector('#addr_after'); 			// 변경 주소
let reset_btnA= document.querySelector('#reset_btnA'); 				// 주소 변경 취소 버튼

let phone_btn = document.querySelector('#phone_btn'); 				// 연락처 변경 버튼
let phone_before = document.querySelector('#phone_before'); 		// 현재 연락처
let phone_after = document.querySelector('#phone_after'); 			// 변경 연락처
let resest_btnP = document.querySelector('#resest_btnP'); 			// 연락처 변경 취소 버튼

let pwd_btn = document.querySelector('#pwd_btn'); 					// 비밀번호 변경 버튼
let pwd_before = document.querySelector('#pwd_before'); 			// 현재 비밀번호
let pwd_after = document.querySelector('#pwd_after'); 				// 변경 비밀번호


addr_btn.addEventListener('click', function() {
	addr_before.classList.replace('on','off');
	addr_after.classList.replace('off','on');
});
reset_btnA.addEventListener('click', function() {
	addr_before.classList.replace('off','on');
	addr_after.classList.replace('on','off');
});
phone_btn.addEventListener('click', function() {
	phone_before.classList.replace('on','off');
	phone_after.classList.replace('off','on');
});
resest_btnP.addEventListener('click', function() {
	phone_before.classList.replace('off','on');
	phone_after.classList.replace('on','off');
});

pwd_btn.addEventListener('click', function() {
	pwd_before.classList.replace('on','off');
	pwd_after.classList.replace('off','on');
});


function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
                
            }
        }).open();
    }

