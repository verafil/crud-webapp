


fetch('api/users')
    .then(res => res.json())
    .then(data => {
        if (data.length > 0) {
            let table = ""
            data.forEach((u) => {
                let userRoles = ""
                u.roles.forEach((r) => {
                    userRoles += r.name.replace('ROLE_', '') + ' '
                })
                table += `
                        <tr>
                            <td>${u.id}</td>
                            <td>${u.name}</td>
                            <td>${u.lastName}</td>
                            <td>${u.username}</td>   
                            <td>${u.age}</td> 
                            <td>${userRoles}</td>     
                           <td><a class="btn btn-primary eBtn" data-bs-toggle="modal"
                                   th:data-bs-target="#modalochka">Edit</a></td>
                            <td><a class="btn bg-danger eBtn" data-bs-toggle="modal"
                                   th:data-bs-target="#modalochka">Delete</a></td>
                        </tr>`
            })
            document.getElementById("data").innerHTML = table
        }
    })

// $(document).ready(function () {
//     $(".table .eBtn").on('click', function (event) {
//         $('.myForm #modalochka').modal()
//
//         // let targetButton = $(event.target);
//         // let buttonUserId = targetButton.attr('data-userid')
//         // let buttonAction = targetButton.attr('data-action')
//         //
//         // defaultModal.attr('data-userid', buttonUserId)
//         // defaultModal.attr('data-action', buttonAction)
//         // defaultModal.modal('show')
//     })
// })

$("#mainTable").find('button').on('click', (event) => {
    let defaultModal = $('#modalochka');

    let targetButton = $(event.target);
    let buttonUserId = targetButton.attr('data-userid');
    let buttonAction = targetButton.attr('data-action');

    defaultModal.attr('data-userid', buttonUserId);
    defaultModal.attr('data-action', buttonAction);
    defaultModal.modal('show');
})