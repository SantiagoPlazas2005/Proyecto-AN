$(document).ready(function () {
    loadData();
});

// Cargar datos en la tabla de Reservas
function loadData() {
    $.ajax({
        url: 'http://localhost:8080/api/Reservas',  // Cambia la URL a la de tu API
        method: 'GET',
        success: function (data) {
            let tableContent = '';
            data.forEach((item) => {
                tableContent += `
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.fecha}</td>
                        <td>${item.hora}</td>
                        <td>${item.usuario.nombre}</td>  <!-- Suponiendo que "usuario" contiene el nombre -->
                        <td>
                            <button class="btn btn-info btn-sm" onclick="editRecord(${item.id})">Editar</button>
                            <button class="btn btn-danger btn-sm" onclick="deleteRecord(${item.id})">Eliminar</button>
                        </td>
                    </tr>`;
            });
            $('#dataBody').html(tableContent);  // Agregar el contenido generado a la tabla
        },
        error: function (error) {
            console.error('Error al cargar datos', error);
        }
    });
}

// Crear o actualizar reserva
function createOrUpdate() {
    const id = $('#id').val();
    const data = {
        fecha: $('#fecha').val(),
        hora: $('#hora').val(),
        usuario: {
            id: $('#usuarioId').val()  // Suponiendo que el ID del usuario se pasa en un campo oculto
        }
    };

    const url = id ? `http://localhost:8080/api/Reservas/${id}` : 'http://localhost:8080/api/Reservas';
    const method = id ? 'PUT' : 'POST';

    $.ajax({
        url: url,
        method: method,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function () {
            resetForm();
            loadData();
            alert(id ? 'Reserva actualizada con éxito' : 'Reserva creada con éxito');
        },
        error: function (error) {
            console.error('Error al guardar datos', error);
        }
    });
}

// Editar reserva
function editRecord(id) {
    $.ajax({
        url: `http://localhost:8080/api/Reservas/${id}`,  // Cambia la URL a la de tu API
        method: 'GET',
        success: function (data) {
            $('#id').val(data.id);
            $('#fecha').val(data.fecha);
            $('#hora').val(data.hora);
            $('#usuarioId').val(data.usuario.id);  // Suponiendo que la información del usuario viene en el objeto "usuario"
            
            $('#submitButton').text('Actualizar');
        },
        error: function (error) {
            console.error('Error al obtener el registro', error);
        }
    });
}

// Eliminar reserva
function deleteRecord(id) {
    if (confirm('¿Estás seguro de eliminar esta reserva?')) {
        $.ajax({
            url: `http://localhost:8080/api/Reservas/${id}`,
            method: 'DELETE',
            success: function () {
                loadData();
                alert('Reserva eliminada con éxito');
            },
            error: function (error) {
                console.error('Error al eliminar reserva', error);
            }
        });
    }
}

// Resetear el formulario
function resetForm() {
    $('#id').val('');
    $('#fecha').val('');
    $('#hora').val('');
    $('#usuarioId').val('');
    
    $('#submitButton').text('Guardar');
}
