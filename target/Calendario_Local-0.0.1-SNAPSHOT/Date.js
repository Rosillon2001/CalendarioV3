
const currentDate = new Date(); // retorna la fecha actual

// El constructor Date tambien es capaz de recibir multiples parametros 
new Date(year, month, date, hour, minute, second, millisecond)
new Date(year, month, date) // SI no recibe alguno de los parametros asumira que son 0

new Date(millisecond) // regresa la fecha indicada por el valor de milisegundos

new Date(dateString) // regresa la fecha indicada en un string que puede tener multiple formato
let birthday = new Date('May 25, 2021 16:00:00')
let birthday = new Date('2021-05-25T16:00:00')



// Datos de una fecha

currentDate.getDate() // fecha del dia del 1 -31 segun el mes
currentDate.getDay() // numero del dia del 0 al 6 siendo 0
currentDate.getMonth() // numero del mes del 0 al 11
currentDate.getFullYear() // año de la fecha en formato largo, ej 2021
currentDate.getTime() // retorna la fecha en terminos de un valor numerico en milisegundos, 
                      // desde enero 1 de 1970 00:00:00 UTC
                      
currentDate.setDate(currentDate.getDate() + 1) // una manera de agregar un dia a una fecha especifica
                                               // una manera muy util para establecer y dar seguimiento a 
                                               // una secuencia de fechas, ya que al momento de que llegue 
                                               // a la ultima fecha de un mes especifico hace la actualizacion
                                               // de todos los atributos como lo es el mes, el año etc 

// datos de una hora

currentDate.getHours() // regresa la hora de la fecha especifica
currentDate.getMinutes() // regresa los minutos de la fecha especifica
currentDate.getSeconds() // regresa los segundos de la fecha especifica
currentDate.getMilliseconds() // regresa los milisegundo de la fecha especifica

// TODO ES RELATIVO AL MOMENTO EN EL CUAL SE CREA EL OBJETO DE LA CLASE DATE Y DEPENDERA TAMBIEN
// DE LOS PARAMETROS QUE SE LE PASE A SU CONSTRUCTOR

dateobject.setHours(hour, min, sec, millisec) // SI no recibe alguno de los parametros 
                                              // asumira los mismo ya establecidos anteriormente


