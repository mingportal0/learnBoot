(function(w, d) {
d.addEventListener("DOMContentLoaded", function(){
	let form = d.getElementById('saveUserForm');
	validate(form, defInput);
	
	d.getElementById('phone').addEventListener('input', function(e) {
		let x = e.target.value.replace(/\D/g, '').match(/(\d{0,3})(\d{0,4})(\d{0,4})/);
		let xx = null;
		
		if(x[2]){
			if(x[0].length === 10){
				if(/(01[1|6|7|8|9])/.test(x[1])){
					xx = e.target.value.replace(/\D/g, '').match(/(01[1|6|7|8|9])(\d{0,3})(\d{0,4})/);
					
				}else{
					xx = e.target.value.replace(/\D/g, '').match(/(010)(\d{0,4})(\d{0,4})/);
				}
			}else{
				xx = e.target.value.replace(/\D/g, '').match(/(01[0|1|6|7|8|9])(\d{0,4})(\d{0,4})/);
			}
			
			e.target.value = xx[1] + '-' + xx[2] + (xx[3] ? '-' + xx[3] : '');
		}else{
			e.target.value = x[1];
		}
	});
});

let defInput = [
	{
		name: "email",
		required : true,
		check: function(input){
			let check1 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i.test(input.value);
			if(!check1) return false;
			return true;
		},
		checkMsg: "이메일 양식에 맞지 않습니다."
	},
	{
		name: "pw",
		required : true,
		check: function(input){
			let check1 = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$/.test(input.value);   //영문,숫자
			let check2 = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,16}$/.test(input.value);  //영문,특수문자
			let check3 = /^(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/.test(input.value);  //특수문자, 숫자
			if(!(check1&&check2&&check3)) return false;
			return true;
		},
		checkMsg: "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요."
	},
	{
		name: "pwCheck",
		required : true,
		equalTo: "input[name='pw']",
		checkMsg: "비밀번호가 일치하지 않습니다."
	},
	{
		name: "name",
		required : true,
	},
	{
		name: "phone",
		required : true,
		check: function(input){
			let check1 = /^(?:(010-\d{4})|(01[1|6|7|8|9]-\d{3,4}))(-\d{4})$/.test(input.value);
			console.log(input.value, check1);
			if(!check1) return false;
			return true;
		},
		checkMsg: "휴대전화 양식에 맞지 않습니다."
	},
];

saveUser = (formId) => {
	let form = d.getElementById(formId);
	if(!checkValidate(form, defInput)){
		return;
	}
	if(!confirm("회원가입을 하시겠습니까?")){
		return;
	}
	form.submit();
}
})(window, document);