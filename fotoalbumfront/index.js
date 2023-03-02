function photoList(){
	axios.get("http://localhost:8080/api/photos").then((list) =>{
		console.log(list);
		document.getElementById("photo_list").innerHTML = '';
		list.data.forEach(photo => {
		document.getElementById("photo_list").innerHTML += `<li>${photo.title}</li> `;
		});
		
	}).catch((list) =>{
	console.error("non si puo eseguire la pagina riprova dopo" , list);
	alert('Errore durante la connessione');
	}); 
}
photoList();