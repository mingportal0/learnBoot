/**
 * document ready
 */

/**
 * logout
 */
 logout = () => {
	if(!confirm("로그아웃 하시겠습니까?")){
		return;
	}
	location.href = "/logoutAction";
}