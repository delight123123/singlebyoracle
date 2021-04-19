

$(function() {
	$("#paymentGo").click(function() {
		if (confirm("결제하시겠습니까?")) {
			var IMP = window.IMP; // 생략가능
			IMP.init('imp97242509'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용

			IMP.request_pay({
				pg: 'inicis', // version 1.1.0부터 지원.
				pay_method: 'card',
				merchant_uid: 'merchant_' + new Date().getTime(),
				name: '주문명:결제테스트',
				amount: 151,
				buyer_email: 'glddld@nate.com',
				buyer_name: 'glddld',
				buyer_tel: '010-1234-5678',
				m_redirect_url: '<c:url value="/paymentList"/>'
			}, function(rsp) {
				if (rsp.success) {
					// jQuery로 HTTP 요청
					jQuery.ajax({
						url: "<c:url value='/verifyIamport'/>", // 가맹점 서버
						method: "POST",
						headers: { "Content-Type": "application/json" },
						data: {
							imp_uid: rsp.imp_uid
						}
					}).done(function(data) {
						//이 아래에 결제테이블에 insert ajax 함수 넣기
						
						
						
						var msg = "결제가 완료되었습니다.";
						msg += '\n고유ID : ' + data.imp_uid;
						msg += '\n상점 거래ID : ' + data.merchant_uid;
						msg += '\n결제 금액 : ' + data.paid_amount;
						msg += '\n카드 승인번호 : ' + data.apply_num;
						alert(msg);
					})
					
				} else {
					var msg = '결제에 실패하였습니다.';
					msg += '에러내용 : ' + rsp.error_msg;
				}

			});



		}
	});
});