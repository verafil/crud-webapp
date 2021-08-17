$(async function () {
    await getTableWithUsers();
    getDefaultModal();
})

const userFetchService = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    },
    // bodyAdd : async function(user) {return {'method': 'POST', 'headers': this.head, 'body': user}},
    findAllUsers: async () => await fetch('api/users'),
    findOneUser: async (id) => await fetch(`api/users/${id}`),
    // addNewUser: async (user) => await fetch('api/users', {
    //     method: 'POST',
    //     headers: userFetchService.head,
    //     body: JSON.stringify(user)
    // }),
    updateUser: async (user) => await fetch(`api/users`, {
        method: 'PUT',
        headers: userFetchService.head,
        body: JSON.stringify(user)
    }),

    deleteUser: async (id) => await fetch(`api/users/${id}`, {method: 'DELETE', headers: userFetchService.head})
}

async function getTableWithUsers() {
    let table = $('#mainTable tbody');
    table.empty();

    await userFetchService.findAllUsers()
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
                            <td>
                                <button data-userid="${u.id}" data-action="edit" class="btn btn-primary eBtn" 
                                data-toggle="modal" data-target="#modalochka">Edit</button>
                             </td>
                            <td>
                               <button data-userid="${u.id}" data-action="delete" class="btn bg-danger eBtn" 
                               data-toggle="modal" data-target="#modalochka">Delete</button>
                            </td>
                        </tr>`
                })
                document.getElementById("data").innerHTML = table
            }
        })

    $("#mainTable").find('button').on('click', (event) => {
        let defaultModal = $('#someDefaultModal');

        let targetButton = $(event.target);
        let buttonUserId = targetButton.attr('data-userid');
        let buttonAction = targetButton.attr('data-action');

        defaultModal.attr('data-userid', buttonUserId);
        defaultModal.attr('data-action', buttonAction);
        defaultModal.modal('show');
    })
}

async function getDefaultModal() {
    $('#someDefaultModal').modal({
        keyboard: true,
       // backdrop: "static",
        show: false
    }).on("show.bs.modal", (event) => {
        let thisModal = $(event.target);
        let userid = thisModal.attr('data-userid');
        let action = thisModal.attr('data-action');
        switch (action) {
            case 'edit':
                editUser(thisModal, userid);
                break;
            case 'delete':
                deleteUser(thisModal, userid);
                break;
        }
    }).on("hidden.bs.modal", (e) => {
        let thisModal = $(e.target);
        thisModal.find('.modal-title').html('');
        thisModal.find('.modal-body').html('');
        thisModal.find('.modal-footer').html('');
    })
}

async function editUser(modal, id) {
    let preuser = await userFetchService.findOneUser(id);
    let user = preuser.json();

    modal.find('.modal-title').html('Edit user');

    let editButton = `<button  class="btn btn-outline-success" id="editButton">Edit</button>`;
    let closeButton = `<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>`
    modal.find('.modal-footer').append(editButton);
    modal.find('.modal-footer').append(closeButton);

    user.then(user => {
        let bodyForm = `
            <form class="form-group" id="editUser">
                <input type="text" class="form-control" id="id" name="id" value="${user.id}" disabled><br>              
                <input class="form-control" type="text" id="username" value="${user.username}"><br>
                <input class="form-control" type="password" id="password"><br>
                <input class="form-control" type="text" id="name" value="${user.name}"><br>
                <input class="form-control" type="text" id="lastName" value="${user.lastName}"><br>
<!--                <input class="form-control" type="text" id="roles" value="${user.roles}"><br>-->
                <input class="form-control" id="age" type="number" value="${user.age}"><br>  
            
            </form>
        `;
        modal.find('.modal-body').append(bodyForm);
    })

    $("#editButton").on('click', async () => {
        let id = modal.find("#id").val().trim();
        let name = modal.find("#name").val().trim();
        let lastName = modal.find("#lastName").val().trim();
        let username = modal.find("#username").val().trim();
        let password = modal.find("#password").val().trim();
        let age = modal.find("#age").val().trim();
        let roles = modal.find("#roles").val();
        let data = {
            id: id,
            name: name,
            lastName: lastName,
            username: username,
            password: password,
            age: age
        }
        const response = await userFetchService.updateUser(data);
        if (response.ok) {
            await getTableWithUsers();
            modal.modal('hide');
        } else {
            // let body = await response.json();
            // let alert = `<div class="alert alert-danger alert-dismissible fade show col-12" role="alert" id="sharaBaraMessageError">
            //                 ${body.info}
            //                 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            //                     <span aria-hidden="true">&times;</span>
            //                 </button>
            //             </div>`;
            // modal.find('.modal-body').prepend(alert);
        }
    })
}


async function deleteUser(modal, id) {
    let preuser = await userFetchService.findOneUser(id);
    let user = preuser.json();

    modal.find('.modal-title').html('Delete user');

    let deleteButton = `<button  class="btn btn-outline-success" id="editButton">Delete</button>`;
    let closeButton = `<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>`
    modal.find('.modal-footer').append(deleteButton);
    modal.find('.modal-footer').append(closeButton);

    user.then(user => {
        let bodyForm = `
            <form class="form-group" id="editUser">
                <input type="text" class="form-control" id="id" name="id" value="${user.id}" disabled><br>              
                <input class="form-control" type="text" id="username" value="${user.username} " disabled><br>
                <input class="form-control" type="password" id="password" disabled><br>
                <input class="form-control" type="text" id="name" value="${user.name}" disabled><br>
                <input class="form-control" type="text" id="lastName" value="${user.lastName}" disabled><br>
<!--                <input class="form-control" type="text" id="roles" value="${user.roles}"><br>-->
                <input class="form-control" id="age" type="number" value="${user.age}" disabled><br>  
            
            </form>
        `;
        modal.find('.modal-body').append(bodyForm);
    })

    $("#editButton").on('click', async () => {
        let id = modal.find("#id").val().trim();
        let name = modal.find("#name").val().trim();
        let lastName = modal.find("#lastName").val().trim();
        let username = modal.find("#username").val().trim();
        let password = modal.find("#password").val().trim();
        let age = modal.find("#age").val().trim();
        let roles = modal.find("#roles").val();
        let data = {
            id: id,
            name: name,
            lastName: lastName,
            username: username,
            password: password,
            age: age
        }
        const response = await userFetchService.deleteUser(id);
        if (response.ok) {
            await getTableWithUsers();
            modal.modal('hide');
        } else {
            // let body = await response.json();
            // let alert = `<div class="alert alert-danger alert-dismissible fade show col-12" role="alert" id="sharaBaraMessageError">
            //                 ${body.info}
            //                 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            //                     <span aria-hidden="true">&times;</span>
            //                 </button>
            //             </div>`;
            // modal.find('.modal-body').prepend(alert);
        }
    })
}

