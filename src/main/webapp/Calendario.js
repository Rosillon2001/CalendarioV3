/**
 * 
 */

var username=document.getElementById('NombreUsuario');
var userdiv=document.getElementById('userCont');
userdiv.appendChild(username);

//-----------------------------------------------------RELOJ----------------------------------------------------------
//funcion de creacion del reloj
function Reloj(){
var tiempo=document.getElementById('hora');
var fecha=document.getElementById('date');
//dias y meses
let dias=['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'];
let meses=['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 
'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

var f=new Date();
let hora=f.getHours();
let min=f.getMinutes();
let sec=f.getSeconds();
let dia=f.getDate();
let mes=f.getMonth();
let ano=f.getFullYear();
let diasemana=f.getDay();


let m=meses[mes];
let d=dias[diasemana];
let hr=(hora>12) ? hora-12: hora;
var ampm;
if(hora>=12 && min>0){
	ampm='PM';
}
else{
	ampm='AM';
}
if(hr==0){
	hr=12;
}
if(min<10){
	min='0'+min;
}
if(sec<10){
	sec='0'+sec;
}

tiempo.textContent=`${hr}:${min}:${sec} ${ampm}`;
fecha.textContent=`${d}, ${dia} de ${m} del ${ano} `;
}
setInterval(Reloj, 1000);

//---------------------------------------------------------Datos del calendario mediante fetch post-----------------------------------------------------------------
var cal=document.getElementById("registro_cal");
var btn=document.getElementById('boton_cal');
var dataCal=[];
var i=0;
const enviarCal=()=>{
	var form=new FormData(cal);
	for (var value of form.values()){
		//console.log(value);
		dataCal[i]=value;
		i++;
	}
	
	const data={
		method:'POST',
		body:form,
		mode:'cors'
	};
	//http://localhost:8080/Calendario_Local/CreateCalendar
	//CreateCalendar
	fetch('CreateCalendar', data).then(response=>{
		return response.json();
	}		
	).
	then(datos =>{
		console.table(datos), 
		console.log(dataCal),
		crear(datos);
	}
	);

}
btn.onclick=enviarCal;

//-----------------------------------------------Crea los div de los calendarios-----------------------------------------------------------------
var caldiv1=document.getElementById('calends1');
function crear(json){
	if(json.status==500){
		window.alert('Un calendario con ese nombre ya existe')
	}else{
		var div=document.createElement('div');
		div.setAttribute('id',json.nombre);
		div.innerHTML="<label style=color:"+json.color+"><input type='checkbox' class=calendario >"+json.nombre+"</label>";	
		caldiv1.appendChild(div);
	}
}
function eliminar(json){
	var div=document.getElementById(`${json.nombre}`);
	div.remove();
}
//--------------------------------------------------Mostrar formulario del calendario------------------------------------------------------------------

var add=document.getElementById('createCal');
var contador=1;

cal.style.display='none';
btn.style.display='none';
function HideNSeek(){
	if(contador%2==0){
		cal.style.display='none';
		btn.style.display='none';
	}
	else{
		cal.style.display='';
		btn.style.display='';
	}
	
	contador++;
}
add.onclick=HideNSeek;


var btndel=document.getElementById('boton_cal_del');
var formdel=document.getElementById('eliminar_cal');
var deletebtn=document.getElementById('eraseCal');
var contador1=1;

btndel.style.display='none';
formdel.style.display='none';

function HideNSeekDel(){
	if(contador1%2==0){
		btndel.style.display='none';
		formdel.style.display='none';
	}
	else{
		btndel.style.display='';
		formdel.style.display='';
	}
	
	contador1++;
}
deletebtn.onclick=HideNSeekDel;

//-----------------------------------------------------Obtener los calendarios correspondientes a la sesion-----------------------------------------------------
var ref=document.getElementById('refresh');
var btnr=document.getElementById('refreshCal');

const recibirCal=()=>{

var options = {
        method: 'GET',
        mode: 'cors'
    };

fetch('CreateCalendar', options).then(response=>{
		return response.json();
	}		
	).
	then(datos =>{
		console.table(datos), 
		console.log(datos),
		crearBar(datos);
	}
	);

}

//-------------------------------------------------------Obtener los datos para la eliminacion del calendario-----------------------------------------------------------
var formdel=document.getElementById('eliminar_cal');

const deleteCal=()=>{
var form=new FormData(formdel);
	for (var value of form.values()){
		console.log(value);
	}
	
	const dataDel={
		method:'DELETE',
		body:form,
		mode:'cors'
	};
	fetch('DeleteCalendar', dataDel).then(response=>{
		return response.json()
	}		
	).
	then(datos =>{
		console.table(datos), 
		console.log(dataCal),
		eliminar(datos);
	}
	);
}

btndel.onclick=deleteCal;
//------------------------------------------------------Contruccion de calendarios (barra lateral)---------------------------------------------------
var caldiv1=document.getElementById('calends1');
function crearBar(Array=[]){
	for(let i=0;i<Array.length;i++){
		if(!!document.getElementById(`${Array[i].nombre}`)==true){//al momento de refrescar los calendarios, se omitiran la creacion de los ya existentes
			console.log("ya existe");
		}else{
			var div=document.createElement('div');
			div.setAttribute('id',Array[i].nombre);
			div.innerHTML="<label style=color:"+Array[i].color+"><input type='checkbox' class=calendario >"+Array[i].nombre+"</label>";	
			caldiv1.appendChild(div);
		}
	}
}


//-------------------------------------------------------------actualizacion de datos del usuario----------------------------------------------------------------
var btnUser=document.getElementById('user');
var formupdate=document.getElementById('modificarUsuario');
var updatebtn=document.getElementById('usuario_update');
var contador2=1;

updatebtn.style.display='none';
formupdate.style.display='none';

function mostrarCredenciales(){
	
	if(contador2%2==0){
		updatebtn.style.display='none';
		formupdate.style.display='none';

	}
	else{
		updatebtn.style.display='';
		formupdate.style.display='';
	}
	
	contador2++;
}
btnUser.onclick=mostrarCredenciales;

const recibirDataUser=()=>{

var options = {
        method: 'GET',
        mode: 'cors'
    };

fetch('ManejoUsuario', options).then(response=>{
		return response.json();
	}		
	).
	then(datos =>{
		console.table(datos), 
		console.log(datos), 
		credentials (datos);
	}
	);

}

const updateUser=()=>{
var form=new FormData(formupdate);
	for (var value of form.values()){
		console.log(value);
	}
	
	const data={
		method:'PUT',
		body:form,
		mode:'cors'
	};
	fetch('ManejoUsuario', data).then(response=>{
		return response.json()
	}		
	).
	then(datos =>{
		console.table(datos), 
		alert(data),
		console.log(dataCal);
	}
	);
}

updatebtn.onclick= function (){
updateUser();
window.location="Login.html";
}

function alert(data){
	window.alert("Credenciales editadas "+`${data.Username}  ${data.Password}`);
}

//funcion para mostrar los datos en los campos
var user=document.getElementById('nombre_usuario');
var pass=document.getElementById('clave_usuario');
function credentials (json){
	user.value=json.Username;
	//pass.value=json.Password;
}

window.onload = function() {
	recibirDataUser();
	recibirCal();
	crearBar();
};