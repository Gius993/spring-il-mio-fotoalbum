function photoList(keyword){
    axios.get("http://localhost:8080/api/photos").then((list) =>{
        console.log(list);
        document.getElementById("photo_list").innerHTML = '';
        list.data
            .filter(photo => photo.title.includes(keyword))
            .forEach(photo => {
                document.getElementById("photo_list").innerHTML += `<li><a href="/service/${photo.id}">${photo.title}</a></li>`;
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
