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

function crear(json){
	var div=document.createElement('div');
	div.setAttribute('id',json.nombre);
	div.innerHTML="<label style=color:"+json.color+"><input type='checkbox' class=calendario >"+json.nombre+"</label>";	
	document.body.appendChild(div);
}
//--------------------------------------------------Mostrar formulario del calendario------------------------------------------------------------------

var add=document.getElementById('createCal');
var contador=0;

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


//-----------------------------------------------------Obtener los calendarios correspondientes a la sesion-----------------------------------------------------
var ref=document.getElementById('refresh');
var btnr=document.getElementById('refreshCal');

const recibirCal=()=>{
const options = {
  	method: "GET", 
	mode: 'cors'
};

// Petición HTTP
fetch("CreateCalendar", options).then(response=>{
		return response.json();
	}		
	).
	then(datos =>{
		console.table(datos), 
		crear(datos);
	}
	);
	
}

btnr.onclick=recibirCal;