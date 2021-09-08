/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function notificar(icon, title, message, type) {
    $.notify({
        icon: icon,
        title: title,
        message: message
    }, {
        type: type,
        delay: 5000,
        allow_dismiss: true,
        newest_on_top: true,
        animate: {
            enter: 'animated fadeInRight',
            exit: 'animated fadeOutRight'
        },
        template: '<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert">' +
            '<span data-notify="title">{1}</span>' +
            '<span data-notify="message">{2}</span>' +
            '</div>'
    });
}

function callWS(url, type, dataType, data) {
    var resultado = '';
    $.ajax({
        url: url,
        type: type,
        dataType: dataType,
        contentType: "application/json",
        data: data,
        async: false,
        cache: false,
        success: function (data) {
//            alert(JSON.stringify(data));
            resultado = data;
        },
        error: function (e, msg) {
//            alert('data: '+data.idTitulacion+ ' error: '+url);
            console.log(msg + ' en ws ' + url);
        }
    });
    return resultado;
}

function llenarComboItems(items, combo, selectedId) {
    var cmb = document.getElementById(combo);
    cmb.length = 1;
    for (var i = 0; i < items.length; i++) {
        var opt = document.createElement('option');
        opt.value = items[i].id;
        opt.innerHTML = items[i].name;
        if (items[i].id == selectedId)
            opt.defaultSelected = true;
        cmb.appendChild(opt);
    }
}

/**
 *
 * @param {type} fecha , en formato MM/dd/yyyy
 * @returns {String}
 */
function formatearFechaConDiaYMes(fecha) {
//    var d, m, y;
//    d = fecha.substring(0, 2);
//    m = fecha.substring(3, 5);
//    y = fecha.substring(6, 10);
//    fecha = m+"/"+d+"/"+y;
    var meses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
    var diasSemana = new Array("DOMINGO", "LUNES", "MARTES", "MIÉRCOLES", "JUEVES", "VIERNES", "SÁBADO");
    var f = new Date(fecha);
    return diasSemana[f.getDay()] + ", " + f.getDate() + " de " + meses[f.getMonth()] + " de " + f.getFullYear();
}

/*
 * Trnasforma una fecha y la decuelve en un formato ordenado para trabajar con el control fecha
 */
function formatearFecha(fecha) {
    var f = new Date(fecha);
    var dia, mes, anio;
    dia = f.getDate();
    mes = (f.getMonth() + 1);
    anio = f.getFullYear();
    if (dia < 10)
        dia = '0' + dia;
    if (mes < 10)
        mes = '0' + mes;
    fecha = anio + '-' + mes + '-' + dia;
    return fecha;
}

/*
 * Trnasforma una fecha y la decuelve en un formato ordenado
 */
function showFecha(fecha) {
    //alert(fecha);
    var f = new Date(fecha);
    var dia, mes, anio;
    dia = f.getDate();
    mes = (f.getMonth() + 1);
    anio = f.getFullYear();
    if (dia < 10)
        dia = '0' + dia;
    if (mes < 10)
        mes = '0' + mes;
    fecha = dia + '-' + mes + '-' + anio;
    return fecha;
}

function showFechaMDY(fecha) {
    //alert(fecha);
    var f = new Date(fecha);
    var dia, mes, anio;
    dia = f.getDate();
    mes = (f.getMonth() + 1);
    anio = f.getFullYear();
    if (dia < 10)
        dia = '0' + dia;
    if (mes < 10)
        mes = '0' + mes;
    fecha = mes + '/' + dia + "/" + anio;
    return fecha;
}

function formatearHora(hora) {
    var h = new Date(hora);
    var horas, minutos, segundos;
    horas = h.getHours();
    minutos = h.getMinutes();
    segundos = h.getSeconds();
    if (h.getHours() < 10)
        horas = '0' + h.getHours();
    if (h.getMinutes() < 10)
        minutos = '0' + h.getMinutes();
    if (h.getSeconds() < 10)
        segundos = '0' + h.getSeconds();
    return horas + ':' + minutos + ':' + segundos;
}

function numeroHorasClase(horaIngreso, horaSalida) {
    var h1 = new Date(horaIngreso);
    var h2 = new Date(horaSalida);
    var numHoras = h2.getTime() - h1.getTime(); //devuelve en milisegundos
    return numHoras / 3600000;
}

function convertirAHora(strHora) {
    var h, m, s;
    h = strHora.substring(0, 2);
    m = strHora.substring(3, 5);
    s = strHora.substring(6, 8);
    var f = new Date();
    f.setHours(h);
    f.setMinutes(m);
    f.setSeconds(s);
    return f;
}

function redireccionar(pagina, cedula, format) {
    //alert('redireccionar 1 : '+pagina + ' / ' + cedula + ' / '+ format);
    if (cedula == null && format == null) {
        window.open(pagina, "mywindow");
    } else {
        var div1 = document.getElementById('buscar');
        var div2 = document.getElementById('listar');

        if (div1.style.display == 'block')
            window.open(pagina + "cedula=" + cedula + "&div=buscar&format=" + format, "mywindow");
        else if (div2.style.display == 'block')
            window.open(pagina + "cedula=" + cedula + "&div=listar&format=" + format, "mywindow");
    }
}

function devolverValorOTextoCombo(combo, devolver) {
    var lista = document.getElementById(combo);
    if (devolver == 'valor')
        return parseInt(lista.options[lista.selectedIndex].value);
    if (devolver == 'texto') {
        return lista.options[lista.selectedIndex].text;
    }
}

function fechaActual() {
    var f = new Date();
    var dia, mes, anio;
    dia = f.getDate();
    mes = (f.getMonth() + 1);
    anio = f.getFullYear();
    if (dia < 10)
        dia = '0' + dia;
    if (mes < 10)
        mes = '0' + mes;
    return mes + '-' + dia + '-' + anio;
}

function fechaActualFormatoConsulta() {
    var f = new Date();
    var dia, mes, anio;
    dia = f.getDate();
    mes = (f.getMonth() + 1);
    anio = f.getFullYear();
    if (dia < 10)
        dia = '0' + dia;
    if (mes < 10)
        mes = '0' + mes;
    return anio + '-' + mes + '-' + dia;
}

//metodo para realizar la paginacion, se usa tambien el script jTPS.js
function paginacion() {
    $('#datos').jTPS({
        perPages: [5, 12, 15, 50, 'TODOS'], scrollStep: 1, scrollDelay: 30,
        clickCallback: function () {
            // target table selector
            var table = '#datos';
            // store pagination + sort in cookie
            document.cookie = 'jTPS=sortasc:' + $(table + ' .sortableHeader').index($(table + ' .sortAsc')) + ',' +
                'sortdesc:' + $(table + ' .sortableHeader').index($(table + ' .sortDesc')) + ',' +
                'page:' + $(table + ' .pageSelector').index($(table + ' .hilightPageSelector')) + ';';
        }
    });

    // reinstate sort and pagination if cookie exists
    var cookies = document.cookie.split(';');
    for (var ci = 0, cie = cookies.length; ci < cie; ci++) {
        var cookie = cookies[ci].split('=');
        if (cookie[0] == 'jTPS') {
            var commands = cookie[1].split(',');
            for (var cm = 0, cme = commands.length; cm < cme; cm++) {
                var command = commands[cm].split(':');
                if (command[0] == 'sortasc' && parseInt(command[1]) >= 0) {
                    $('#datos .sortableHeader:eq(' + parseInt(command[1]) + ')').click();
                } else if (command[0] == 'sortdesc' && parseInt(command[1]) >= 0) {
                    $('#datos .sortableHeader:eq(' + parseInt(command[1]) + ')').click().click();
                } else if (command[0] == 'page' && parseInt(command[1]) >= 0) {
                    $('#datos .pageSelector:eq(' + parseInt(command[1]) + ')').click();
                }
            }
        }
    }

    // bind mouseover for each tbody row and change cell (td) hover style
    $('#datos tbody tr:not(.stubCell)').bind('mouseover mouseout',
        function (e) {
            // hilight the row
            e.type == 'mouseover' ? $(this).children('td').addClass('hilightRow') : $(this).children('td').removeClass('hilightRow');
        }
    );
}

function deleteRow(tableID) {
    try {
        var tbody = document.getElementById(tableID).getElementsByTagName("tbody")[0];
        var rowCount = tbody.rows.length;
        //alert('a eliminar: '+rowCount);
        for (var i = 0; i < rowCount;) {
            tbody.deleteRow(i);
            rowCount--;
        }
    } catch (e) {
        alert(e);
    }
}

function invertirFecha(fecha, separador) {
//    alert(fecha+" "+separador);
    var dia, mes, anio;
    dia = fecha.substring(0, 2);
    mes = fecha.substring(3, 5);
    anio = fecha.substring(6, 10);
//    fecha = anio + '-' + mes + '-' + dia;
    fecha = mes + separador + dia + separador + anio;
    return fecha;
}

function agregarElementoACombo(combo, elemento, id, tooltip, deshabilitar) {
    select = document.getElementById(combo);
    var opt = document.createElement('option');
    opt.value = id;
    opt.innerHTML = elemento;
    opt.disabled = deshabilitar;
    if (tooltip != undefined) {
        opt.title = tooltip;
        opt.style = 'background:#ffcccc;';
//        opt.disabled = true;
    }
    select.appendChild(opt);
}

/**
 *
 * @param {String} dia ->indicamos el día de la semana
 * @param {String} fecha_ini ->indicamos fecha de inicio de semana   (MM/dd/yyyy)
 * @param {String} fecha_fin ->indicamos fecha de fin de semana    (MM/dd/yyyy)
 * @returns {String} -> Fecha que corresponde al día indicado, comprendido entre las fechas
 */
function generarFechaClase(dia, fecha_ini, fecha_fin) {
//    alert(invertirFecha(fecha_ini, '/'));
    dia = dia.toUpperCase();
    var dias = ["LUNES", "MARTES", "MIÉRCOLES", "JUEVES", "VIERNES", "SÁBADO", "DOMINGO"];
    var pos = dias.indexOf(dia);
    var itr = moment.twix(new Date(fecha_ini), new Date(fecha_fin)).iterate("days");
    var range = [];
    while (itr.hasNext()) {
        range.push(itr.next().toDate())
    }
    return range[pos];
}

/**
 *
 * @param {String} dia ->indicamos el día de la semana
 * @param {String} fecha_ini ->indicamos fecha de inicio de semana   (MM/dd/yyyy)
 * @param {String} fecha_fin ->indicamos fecha de fin de semana    (MM/dd/yyyy)
 * @returns {String} -> Fecha que corresponde al día indicado, comprendido entre las fechas
 */
function generarFechasClases(dia, fecha_ini, fecha_fin) {
    dia = dia.toUpperCase();
    var dias = ["LUNES", "MARTES", "MIÉRCOLES", "JUEVES", "VIERNES", "SÁBADO", "DOMINGO"];
    var pos = dias.indexOf(dia);
    var itr = moment.twix(new Date(fecha_ini), new Date(fecha_fin)).iterate("days");
    var range = [];
    while (itr.hasNext()) {
        var f = itr.next().toDate();
        var d = formatearFechaConDiaYMes(f);
        if (d.indexOf(dia) !== -1) {
            range.push(f);
        }
    }
    return range;
}

function llenarCombo(combo, min, max, intervalo, activo) {
    select = document.getElementById(combo);
    for (var i = min; i <= max; i = i + intervalo) {
        var opt = document.createElement('option');
        var val = i < 10 ? ('0' + i) : i;
        opt.value = val;
        opt.innerHTML = val;
        if (i == activo)
            opt.defaultSelected = true;
        select.appendChild(opt);
    }
}

function resaltar(t) {
    if (t.checked == true)
        $(t.parentNode.parentNode).addClass('bg-success');
    else
        $(t.parentNode.parentNode).removeClass('bg-success');
}

function addSelectAll(check, clase) {
    $('#' + check).click(function (event) {
        if (this.checked) {
            // Iterate each checkbox
            $(':checkbox.' + clase).each(function (i) {
                this.checked = true;
                resaltar(this);
            });
        } else {
            $(':checkbox.' + clase).each(function (i) {
                this.checked = false;
                resaltar(this);
            });
        }
    });
}

/*
 *      librerías necesarias para paginar tabla, agregar botones de desacarga y agregar búsqueda
 *      <link rel="stylesheet" href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css"/>
 <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.1.0/css/buttons.dataTables.min.css"/>
 <script language="JavaScript" type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
 <script language="JavaScript" type="text/javascript" src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
 <script language="JavaScript" type="text/javascript" src="https://cdn.datatables.net/buttons/1.1.0/js/dataTables.buttons.min.js"></script>
 <script language="JavaScript" type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
 <script language="JavaScript" type="text/javascript" src="http://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
 <script language="JavaScript" type="text/javascript" src="http://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
 <script language="JavaScript" type="text/javascript" src="http://cdn.datatables.net/buttons/1.1.0/js/buttons.html5.min.js "></script>
 */
function tabla(tabla) {
    $('#' + tabla).DataTable({
        "lengthMenu": [
            [20, 10, 5, -1],
            ['20 registros', '10 registros', '5 registros', 'Mostrar todos']
        ],
        initComplete: function () {
            this.api().columns().every(function () {
                var column = this;
                var select = $('<select><option value=""></option></select>')
                    .appendTo($(column.footer()).empty())
                    .on('change', function () {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );
                        column
                            .search(val ? '^' + val + '$' : '', true, false)
                            .draw();
                    });
                column.data().unique().sort().each(function (d, j) {
                    select.append('<option value="' + d + '">' + d + '</option>')
                });
            });
        },
        language: {
            buttons: {
                selectAll: "Select all items",
                selectNone: "Select none"
            }
        },
        dom: 'Bfrtip', //Bfrtip       iBpftr
//                    stateSave: true, //mantiene en caché las columnas seleccionadas para mostrar
        buttons: [
            'copy', 'excel', 'pdf'
        ]
    });
}

function tabla2(tabla) {
    $('#' + tabla).DataTable({
        "destroy": true,
        "searching": true,
        "bPaginate": true,
//                "bLengthChange": true,
        "bFilter": true,
        "bSort": true,
        "bInfo": true,
        "bAutoWidth": true,
        "bJQueryUI": false,
        "lengthMenu": [
            [20, 10, 5, -1],
            ['20 registros', '10 registros', '5 registros', 'Mostrar todos']
        ],
        initComplete: function () {
            this.api().columns().every(function () {
                var column = this;
                var select = $('<select><option value=""></option></select>')
                    .appendTo($(column.footer()).empty())
                    .on('change', function () {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );
                        column
                            .search(val ? '^' + val + '$' : '', true, false)
                            .draw();
                    });
                column.data().unique().sort().each(function (d, j) {
                    select.append('<option value="' + d + '">' + d + '</option>')
                });
            });
        },
        language: {
            buttons: {
                selectAll: "Select all items",
                selectNone: "Select none"
            }
        },
        dom: 'Bfrtip', //Bfrtip       iBpftr
//                    stateSave: true, //mantiene en caché las columnas seleccionadas para mostrar
        buttons: [
            'copy', 'excel', 'pdf'
        ]
    });
}

function docente(url) {
    var docente = callWS(url, 'GET', 'JSON', {});
    var html = '<small><table class="table table-condensed table-responsive">' +
        '<tr><th>periodo</th><th>modalidad</th><th>perfil</th><th>jornada</th><th>area</th><th>departamento</th><th>seccion</th></tr>';
    $.each(docente, function (i, item) {
        html += '<tr> ' +
            '<td>' + item.periodo + '</td>' +
            '<td>' + item.modalidad + '</td>' +
            '<td>' + item.perfil + '</td>' +
            '<td>' + item.jornada + '</td>' +
            '<td>' + item.area + '</td>' +
            '<td>' + item.departamento + '</td>' +
            '<td>' + item.seccion + '</td>' +
            '</tr>';
    });
    html += '</table></small>';
    $('#mdl_modal').modal('show');
    $('#mdl_label').html('Detalle docente');
    $('#mdl_body').html(html);
}

function confirmar(id, action, mensaje, clase, icon, text) {
    var tbl = '<form action="' + action + '" method="POST">' +
        '<input type="hidden" id="id" name="id" value="' + id + '" />' +
        '<label class="label label-default">comentario:</label>' +
        '<textarea type="text" name="comentario" id="comentario" rows="4" cols="50" style="width:100%;" placeholder="ingrese motivo por el cuál realiza la acción de ' + text + '" required="required"></textarea>' +
        '<br/><button class="center-block btn btn-' + clase + '" > <span class="glyphicon glyphicon-' + icon + '">' + text + '</span></button>' +
        '</form>';
    $('#mdl_body').html(tbl);
    $('#mdl_label').html(mensaje);
    $('#mdl_modal').modal('show');
}