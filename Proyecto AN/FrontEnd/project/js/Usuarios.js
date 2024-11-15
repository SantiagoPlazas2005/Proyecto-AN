$(document).ready(function () {
    loadData();
});

// Cargar datos en la tabla
function loadData() {
    $.ajax({
        url: 'http://localhost:8080/api/usuarios',
        method: 'GET',
        success: function (data) {
            let tableContent = '';
            data.forEach((item) => {
                tableContent += `
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.documento}</td>
                        <td>${item.nombre}</td>
                        <td>${item.correo}</td>
                        <td>
                            <button class="btn btn-info btn-sm" onclick="editRecord(${item.id})">Editar</button>
                            <button class="btn btn-danger btn-sm" onclick="deleteRecord(${item.id})">Eliminar</button>
                        </td>
                    </tr>`;
            });
            $('#dataBody').html(tableContent);
        },
        error: function (error) {
            console.error('Error al cargar datos', error);
        }
    });
}

function createOrUpdate() {
    const id = $('#id').val();
    const data = {
        documento: $('#documento').val(),
        nombre: $('#nombre').val(),
        correo: $('#correo').val()
    };

    const url = id ? `http://localhost:8080/api/usuarios/${id}` : 'http://localhost:8080/api/usuarios';
    const method = id ? 'PUT' : 'POST';

    $.ajax({
        url: url,
        method: method,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function () {
            resetForm();
            loadData();
            alert(id ? 'Registro actualizado con éxito' : 'Registro creado con éxito');
        },
        error: function (error) {
            console.error('Error al guardar datos', error);
        }
    });
}

// Editar registro
function editRecord(id) {
    $.ajax({
        url: `http://localhost:8080/api/usuarios/${id}`,
        method: 'GET',
        success: function (data) {
            $('#id').val(data.id);
            $('#documento').val(data.documento);
            $('#nombre').val(data.nombre);
            $('#correo').val(data.correo);
            
            $('#submitButton').text('Actualizar');
        },
        error: function (error) {
            console.error('Error al obtener el registro', error);
        }
    });
}

function resetForm() {
    $('#id').val('');
    $('#documento').val('');
    $('#nombre').val('');
    $('#correo').val('');
    
    $('#submitButton').text('Guardar');
}

// Eliminar registro
function deleteRecord(id) {
    if (confirm('¿Estás seguro de eliminar este registro?')) {
        $.ajax({
            url: `http://localhost:8080/api/usuarios/${id}`,
            method: 'DELETE',
            success: function () {
                loadData();
                alert('Registro eliminado con éxito');
            },
            error: function (error) {
                console.error('Error al eliminar registro', error);
            }
        });
    }
}
function resetForm(){
    $('#id').val('');
    $('#crudForm')[0].reset();
}