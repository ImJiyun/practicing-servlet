$("#boardWriteBtn").click(async function() {
	const writer = $("#boardWriter").val();
	const content = $("#boardContent").val();
	
	let data = {sign : "boardWrite", writer, content};
	data = JSON.stringify(data); // json text 형태로 변환
	// 요청 보내기 
    data = await fetch("main", {method : "post", body : data}); // response 객체 
	data = await data.json(); // js 객체를 얻게 됨 
	if (data.msg) {
		alert(data.msg);
	}
});

$(document).on("click", "#logoutBtn", logout); // event name, event object(element), call back function 
// logoutBtn은 동적으로 만들어짐 
// on을 통해 이벤트 처리 가능

function logout() {
	let data = {sign : "logout"};
	// 요청 보내기 
	fetch("main", {method : "post", body : data});
	$.removeCookie('name'); 
	$.removeCookie('JSESSIONID'); // js로 접근할 수 없는 쿠키 -> 보안 쿠키 
	document.location.href = "index.html";
	
} 

let loginedBoardWhiteSpanText = `<a href="#" class="text-white" data-bs-toggle="modal" data-bs-target="#boardWriteModal">글쓰기</a>`;

// 새로고침해도 로그인 유지 되도록 
const loginedName = $.cookie('name');
if (loginedName) {
	$(loginDiv).html(`${loginedName}님 환영합니다.<button id="logoutBtn">logout</button>`);
	$(boardWriteSpan).html(loginedBoardWhiteSpanText);
	// 비동기 - CSR, 화면의 부분만 바뀌도록 
	
	$(boardWriter).val(loginedName);
	
}

// 로그인 했을 때 
$("#loginBtn").click(async function() {
	const id = $("#id").val(); 
	const pw = $("#pw").val();
	let data = {id, pw, sign : "login"};
	console.log(data);
	data = JSON.stringify(data); // json text 형태로 변환
	console.log(data);
	
	// xhr 객체를 통해 보냄 - 현재는 fetch 이용
	// 비동기 요청 
	data = await fetch("main", {method : "post", body : data}); // url : servlet의 별명, option : method, body에는 보내는 data
	console.log(data); // await 없으면 promise 객체, 있으면 response 객체 
	data = await data.json(); // 자바스크립트 객체 반환 
	console.log(data);
	
	if (data.name) {
		$.cookie('name', data.name);
		$(loginDiv).html(`${data.name}님 환영합니다.<button id="logoutBtn">logout</button>`);
		$(boardWriteSpan).html(loginedBoardWhiteSpanText);
		$(boardWriter).val(loginedName);
	} else {
		alert(`${data.msg}`);
	}
});