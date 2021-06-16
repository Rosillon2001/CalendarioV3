/**
 * 
 */

var username=document.getElementById('NombreUsuario');
var userdiv=document.getElementById('userCont');
userdiv.appendChild(username);

var sol=document.getElementById('reloj-am');
var luna=document.getElementById('reloj-pm');
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
	sol.style.display='none';
	luna.style.display='';
}
else{
	ampm='AM';
	sol.style.display='';
	luna.style.display='none';
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

//---------------------------------------------------------Envio de datos del calendario mediante fetch post-----------------------------------------------------------------
var cal=document.getElementById("registro_cal");
var btn=document.getElementById('boton_cal');


const enviarCal=()=>{
	var form=new FormData(cal);
	for (var value of form.values()){
		console.log(value);
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
		div.innerHTML="<a style=color:"+json.color+"><input type='checkbox' class=calendario >"+json.nombre+"</a>";	
		caldiv1.appendChild(div);
	}
}
function eliminar(obj){
	var div=document.getElementById(`${obj}`);
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
		btndel.style.display='none';
		formdel.style.display='none';
		updatebtn.style.display='none';
		formupdate.style.display='none';
		cerses.style.display='none';
		activityform.style.display='none';
		activitybutton.style.display='none';
		actdiv1.style.display='none';
		deleteuser.style.display='none';
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
		cal.style.display='none';
		btn.style.display='none';
		updatebtn.style.display='none';
		formupdate.style.display='none';
		cerses.style.display='none';
		activityform.style.display='none';
		activitybutton.style.display='none';
		actdiv1.style.display='none';
		deleteuser.style.display='none';
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
		console.log(value), 
		eliminar(value);
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
		console.table(datos);
	}
	);
}

btndel.onclick=deleteCal;
//------------------------------------------------------Contruccion de calendarios (barra lateral)---------------------------------------------------
var caldiv1=document.getElementById('calends1');
function crearBar(Array=[]){
	if(Array.length>0){
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
	}else{
		console.log("No hay calendarios asociados a este usuario");
	}
}

var act=document.getElementById('actividades');

//act.onclick=actCal;
//-------------------------------------------------------------actualizacion de datos del usuario----------------------------------------------------------------
var btnUser=document.getElementById('user');
var formupdate=document.getElementById('modificarUsuario');
var updatebtn=document.getElementById('usuario_update');
var cerses=document.getElementById('cerrar_sesion');
var deleteuser=document.getElementById('eliminar_cuenta');

var contador2=1;

updatebtn.style.display='none';
formupdate.style.display='none';
cerses.style.display='none';
deleteuser.style.display='none';

function mostrarCredenciales(){
	
	if(contador2%2==0){
		updatebtn.style.display='none';
		formupdate.style.display='none';
		cerses.style.display='none';
		deleteuser.style.display='none';
	}
	else{
		updatebtn.style.display='';
		formupdate.style.display='';
		cerses.style.display='';
		deleteuser.style.display='';
		cal.style.display='none';
		btn.style.display='none';
		btndel.style.display='none';
		formdel.style.display='none';
		activityform.style.display='none';
		activitybutton.style.display='none';
		actdiv1.style.display='none';
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
		console.log(dataCal);
	}
	);
}

updatebtn.onclick= function (){
updateUser();
window.location="Login.html";
}

//-------------------------------------------------------------------------gestion de usuario-----------------------------------------------

//funcion para mostrar los datos en los campos del usuario
var user=document.getElementById('nombre_usuario');
var pass=document.getElementById('clave_usuario');
function credentials (json){
	user.value=json.Username;
	//pass.value=json.Password;
}


//-----------------------------------------------------------------------Creacion de actividades---------------------------------------------------
var activityform=document.getElementById('calend_activity');
var activitybutton=document.getElementById('act_btn');

const enviarAct=()=>{
	var form=new FormData(activityform);
	for (var value of form.values()){
		console.log(value);
	}
	
	const data={
		method:'POST',
		body:form,
		mode:'cors'
	};
	fetch('Actividades', data).then(response=>{
		return response.json();
	}		
	).
	then(datos =>{
		console.table(datos), 
		crearAct(datos),
		console.log(datos);
	}
	);

}
activitybutton.onclick=enviarAct;


//-------------------------------------------------------------------
var actdiv1=document.getElementById('activities');

function crearAct(json){
	if(json.status==500){
		window.alert('Actividad no ha sido creada')
	}else{
		var div=document.createElement('div');
		div.setAttribute('id',json.nombre);
		div.innerHTML="<a style=color:"+json.color+"><input type='checkbox' class=actividad>"+json.IdActividad+"."+json.nombre+"</a>";	
		actdiv1.appendChild(div);
	}
}

//ocultar form y botones
var activityform=document.getElementById('calend_activity');
var activitybutton=document.getElementById('act_btn');
var actdiv1=document.getElementById('activities');
var btnAct=document.getElementById('actCal');

contador3=1;

activityform.style.display='none';
activitybutton.style.display='none';
actdiv1.style.display='none';

function HideNSeekAct(){
	if(contador3%2==0){
		activityform.style.display='none';
		activitybutton.style.display='none';
		actdiv1.style.display='none';

	}
	else{
		activityform.style.display='';
		activitybutton.style.display='';
		actdiv1.style.display='';
		updatebtn.style.display='none';
		formupdate.style.display='none';
		cerses.style.display='none';
		cal.style.display='none';
		btn.style.display='none';
		btndel.style.display='none';
		formdel.style.display='none';
		deleteuser.style.display='none';
	}
	
	contador3++;
}

btnAct.onclick=HideNSeekAct;


//funcion de eliminacion de objetos del DOM
function eliminar(obj){
	var div=document.getElementById(`${obj}`);
	div.remove();
}
//---------------------------------------------------------------------------------------------------------------------------------------

window.onload = function() {
	recibirDataUser();
	recibirCal();
	crearBar();
};