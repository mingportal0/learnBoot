/*
	set Left Menu
*/
window.setMenu = (list) =>{
	let i = list.length;
	let ul = document.getElementById("left_nav");
	
	while(i--){
		let li = document.createElement("li");
		li.classList.add("left_navItem");
		
		let a = document.createElement("a");
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