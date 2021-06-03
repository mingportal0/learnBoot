/*
	javascript iframe modal
*/
(function (w, d) {
/*
	open modal
*/
w.openModal = (id, modalTitle, link, width, height) => {
	initModal(id, modalTitle, link, width, height);
}

/*
	close modal
*/
w.closeModal = () => {
	let modal = d.querySelector(".modal");
	modal.remove();
}

/*
	init modal
*/
let initModal = (id, modalTitle, link, width, height) => {
	let content = d.getElementById(id);
	_createModal(content, modalTitle, link, width, height);
}

let _createModal = (content, modalTitle, link, width, height) => {
	let div = _createClassDiv(["modal"]);
	
	_createModalDialog(div, modalTitle, link, width, height);
	_createModalFade(div);
	
	content.appendChild(div);
}

let _createModalFade = (content) => {
	let div = _createClassDiv(["modal-fade", "fade", "show"]);
	div.setAttribute("id", "modal-fade");
	div.addEventListener("click", closeModal);
	
	content.appendChild(div);
}

let _createModalDialog = (modal, modalTitle, link, width, height) => {
	let div = _createClassDiv(["modal-dialog", "fade", "show"]);
	div.style = "width: " + width + "px; height: " + height + "px;";
	div.setAttribute("role", "dialog");
	div.setAttribute("id", "modal");
	div.setAttribute("aria-labelledby", "myModalLabel");
	div.setAttribute("aria-hidden", "true");
	
	_createModalHeader(div, modalTitle);
	_createModalContent(div, link);
	modal.appendChild(div);
}

let _createModalHeader = (modal, modalTitle) => {
	let div = _createClassDiv(["modal-header"]);
	
	_createModalTitle(div, modalTitle);
	_createModalClose(div);
	modal.appendChild(div);
}

let _createModalTitle = (modal, modalTitle) => {
	let div = _createClassDiv(["modal-title"]);
	div.innerHTML = modalTitle;
	modal.appendChild(div);
}

let _createModalClose = (modal) => {
	let div = _createClassDiv(["modal-close"]);
	div.addEventListener("click", closeModal);
	modal.appendChild(div);
}

let _createModalContent = (modal, link) => {
	let div = _createClassDiv(["modal-content"]);
	
	_createModalIframe(div, link);
	modal.appendChild(div);
}

let _createModalIframe = (modal, link) => {
	let iframe = d.createElement("iframe");
	
	iframe.setAttribute("src", link);
	iframe.setAttribute("width", "100%");
	iframe.setAttribute("height", "100%");
	iframe.setAttribute("frameborder", "1");
	iframe.setAttribute("scrolling", "yes");
	iframe.setAttribute("auto-config", "true");
	
	modal.appendChild(iframe);
}

let _createClassDiv = (cList) => {
	let i = cList.length;
	let div = d.createElement("div");
	
	while(i--){
		div.classList.add(cList[i]);;
	}
	return div;
}

/*
	show/hide Modal
*/
let toggleModal = () => {
	let modal = d.getElementById("modal");
	let modalFade = d.getElementById("modal-fade");

	modal.classList.toggle("show");
	modalFade.classList.toggle("show");
}
}(window, document));
