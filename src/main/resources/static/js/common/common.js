(function(w, d) {
/*
	set Left Menu
*/
w.setMenu = (list) =>{
	let i = list.length;
	let ul = d.getElementById("left_nav");
	
	while(i--){
		let li = d.createElement("li");
		li.classList.add("left_navItem");
		
		let a = d.createElement("a");
		if(list[i].class){
			a.classList.add(list[i].class);
		}
		if(list[i].id){
			a.id = list[i].id;
		}
		if(list[i].data){
			let keys = Object.keys(list[i].data);
			let k = keys.length;
			while(k--){
				a.dataset[keys[k]] = list[i].data[k];
				console.log(keys[k], list[i].data[keys[k]]);
			}
			
		}
		if(list[i].link){
			a.href = list[i].link;
		}
		if(list[i].name){
			a.innerHTML = list[i].name;
		}
		
		li.appendChild(a);
		ul.appendChild(li);
	}
}

/*
	get data
*/
w.getData = (elemId) => {
	let box = d.getElementById(elemId);
	let itemList = [];
	let inputList = box.querySelectorAll("input[type='text'], input[type='hidden'], textarea");
	let selList = box.querySelectorAll("select");
	for(let input of inputList){
		let item = new Object();
		item.name = input.name;
		item.value = input.value;
		itemList.push(item);
	}
	for(let si=0; si<selList.length; si++){
		let item = new Object();
		let sel = selList[si];
		let selOpt = sel.options[sel.selectedIndex];
		item.name = sel.name;
		item.value = selOpt.value;
		itemList.push(item);
	}
	
	return itemList;
	
}

/*
	Element Name, Value 가져오기
*/
getEleDataList = (td) => {
	let itemList = [];
	let inputList = td.querySelectorAll("input[type='text'], input[type='hidden'], textarea");
	let selList = td.querySelectorAll("select");
	for(let ii=0; ii<inputList.length; ii++){
		let item = new Object();
		let input = inputList[ii];
		item.name = input.name;
		item.value = input.value;
		itemList.push(item);
	}
	for(let si=0; si<selList.length; si++){
		let item = new Object();
		let sel = selList[si];
		let selOpt = sel.options[sel.selectedIndex];
		item.name = sel.name;
		item.value = selOpt.value;
		itemList.push(item);
	}
	return itemList;
}
})(window, document);
