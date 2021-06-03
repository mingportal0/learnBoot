/**
 * set Left Menu
 */
(function(w, d) {
	let list = [
		{id: "openSaveDocBtn", name: "글쓰기", link: "javascript:openModal('listDoc', '글쓰기', 'http://localhost:8080/doc/saveDoc', 700, 500)"},
		{name: "검색", link: "/doc/listDoc"}
	];
	setMenu(list);
})(window, document);