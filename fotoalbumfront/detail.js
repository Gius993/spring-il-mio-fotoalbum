const URLParams=new URLSearchParams(window.location.search);
const photoId =URLParams.get('id');

axios.get(`http://localhost:8080/api/photos/${photoId}`).then((list) => {
    console.log( list);
   
    document.querySelector('#title').innerHTML= list.data.title;
    document.querySelector('#url').innerHTML= list.data.url;
  
	
    
    document.getElementById("home_page").innerHTML += `<a href="index.html">vai a home</a>`;
	
}).catch((list) =>{
	console.error("non si puo eseguire la pagina riprova dopo" , list);
	alert('Errore durante la connessione');
}); 
// invio commenti
const commentForm = document.querySelector('#comment-form');

commentForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const commentContent = document.querySelector('#comment-input').value;

    axios.post(`http://localhost:8080/api/photos/comment/${photoId}`, {
        text: commentContent
    })
    .then((response) => {
        console.log(response.data);

      
        window.location.reload();
    })
    .catch((error) => {
        console.error(error);
    });
});
// stampo i commenti
axios.get(`http://localhost:8080/api/photos/${photoId}`)
.then((response) => {
	console.log(response.data); 
    const photo = response.data.text;
	axios.get(`http://localhost:8080/api/comments?photo_id=${photoId}`)
	.then((response) => {
	  const comments = response.data;
    


    const commentsList = document.querySelector('#comments-list');

    comments.forEach((comment) => {
        const commentItem = document.createElement('li');
        commentItem.innerText = comment.content;
        commentsList.appendChild(commentItem);
    });
})})
.catch((error) => {
    console.error(error);
});