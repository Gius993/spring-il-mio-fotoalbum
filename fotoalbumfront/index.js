function photoList(keyword){
    axios.get("http://localhost:8080/api/photos").then((list) =>{
        console.log(list);
        document.getElementById("photo_list").innerHTML = '';
        list.data
            .filter(photo => photo.title.includes(keyword)) // filtra le foto in base alla parola chiave
            .forEach(photo => {
                document.getElementById("photo_list").innerHTML += `<li>${photo.title}</li> `;
            });
        
    }).catch((list) =>{
        console.error("non si puo eseguire la pagina riprova dopo" , list);
        alert('Errore durante la connessione');
    }); 
}
function searchPhotos(){
	const keyword = document.getElementById("keyword").value;
	photoList(keyword);
  }
  photoList(keyword);
