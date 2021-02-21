<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<html lang="ru">
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            table-layout: fixed;
            border: 1px black solid;
            padding: 0;

        }

        #userFormTable{
            margin-top: -1px;

        }

        #userFormTable td {
            padding: 3px 3px ;
            border-right: 1px black solid;
        }

        #thUserId, #tdUserFormId {
            width: 3%;
            min-width: 30px;
        }

        #thUserName, #tdUserFormName {
            width: 10%;
            min-width: 150px;
        }

        #thUserEmail, #tdUserFormEmail {
            width: 30%;
            min-width: 200px;
        }

        #thUserGender, #tdUserFormGender{
            width: 8%;
            min-width: 100px;
        }

        #thUserBirthdate, #tdUserFormBirthdate {
            width: 10%;
            min-width: 180px;
        }
        tr {
            vertical-align: top;
        }

        #userTable td{
            margin: 0;
            padding: 3px;
            border-right: 1px black solid;
        }

        #userTable{
            border: 1px black solid;
            /*border-collapse: collapse;*/
        }

        #userInputId {
        }

        div {
            width: 100%;
        }

        input {
            box-sizing: content-box;
            margin:0;
            border:0;
            outline:0;
            width:98%;
            padding: 0 0px;


        }
    </style>

</head>
<body>
Проверка
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<div>
    <table id="userTable" class="userTable">
        <tr>
            <th id="thUserId">id</th>
            <th id="thUserName">name</th>
            <th id="thUserEmail">e-mail</th>
            <th id="thUserBirthdate">birthdate</th>
            <th id="thUserGender">gender</th>
            <th id="thUserCarCollection">car collection</th>
        </tr>
    </table>
    <script type="text/javascript" charset="utf-8">

        javaList = ${userList};
        javaList.forEach(function(itemUser, i, arrUser) {
            $('#userTable').append($('<tr class="userTableRow" onclick="selectUser(this)">')
                .append($('<td>').append(itemUser.id))
                .append($('<td>').append(itemUser.name))
                .append($('<td>').append(itemUser.email))
                .append($('<td>').append(itemUser.birthdate))
                .append($('<td>').append(itemUser.gender))
                .append($('<td>').append($('<table id = "carTable'+i+'" class="carTable" >')))
            );

            itemUser.carEntities.forEach(function(itemCar, j, arrCar) {
                $('#carTable'+i).append($('<tr>')
                    .append($('<td>').append(itemCar.id))
                    .append($('<td>').append(itemCar.brend))
                    .append($('<td>').append(itemCar.model))
                    .append($('<td>').append(itemCar.year_of_release)))


            });

        });

    </script>
    <form id="benderform" action="/" method="post" enctype="application/json">
    <table id="userFormTable" width="100px">
        <tr>
            <td id="tdUserFormId"><input type="text" id="userInputId" name="id" value="1" readonly></td>
            <td id="tdUserFormName"><input type="text" id="userInputName" name="name"></td>
            <td id="tdUserFormEmail"><input type="email" id="userInputEmail" name="email"></td>
            <td id="tdUserFormBirthdate"><input type="date" id="userInputBirthdate" name="birthdate" data-date-format="YYYY-MMMM-DD"></td>
            <td id="tdUserFormGender"><select id="userInputGender" name="gender" value="">
                                <option value="0" name="male" selected = false>male</option>
                                <option value="1" selected = false>female</option>
                            </select></td>
            <td><input type="submit" id="userInputButton" value="Добавить пользователя"></td>
            <td><input type="button" id="userInputButtonDelete" onclick="" value="Удалить"></td>
        </tr>
    </table>
    </form>



    <script>
        document.getElementById('benderform').addEventListener('submit', submitForm);


        function submitForm(event) {
            // Отменяем стандартное поведение браузера с отправкой формы
            event.preventDefault();

            // event.target — это HTML-элемент form
            let formData = new FormData(event.target);

            // Собираем данные формы в объект
            let obj = {};
            formData.forEach((value, key) => obj[key] = value);
            // Собираем запрос к серверу
            let request = new Request(event.target.action, {
                method: 'POST',
                body: JSON.stringify(obj),
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            // Отправляем (асинхронно!)
            fetch(request).then(
                function(response) {
                    // Запрос успешно выполнен
                    console.log(response);
                    // return response.json() и так далее см. документацию
                    location.reload(true); /*true - загрузка с сервера , false - с кеша*/
                },
                function(error) {
                    // Запрос не получилось отправить
                    console.error(error);
                }
            );

            // Код после fetch выполнится ПЕРЕД получением ответа
            // на запрос, потому что запрос выполняется асинхронно,
            // отдельно от основного кода
            console.log('Запрос отправляется');
        }
    </script>

    <script>
        document.getElementById("userInputGender").value = null;

        function selectUser(thisRow){
            document.getElementById("userInputId").innerText = thisRow.firstChild.innerText;
            document.getElementById("userInputId").value = thisRow.firstChild.innerText;
            document.getElementById("userInputName").innerText = thisRow.childNodes[1].innerText;
            document.getElementById("userInputName").value = thisRow.childNodes[1].innerText;
            document.getElementById("userInputEmail").innerText = thisRow.childNodes[2].innerText;
            document.getElementById("userInputEmail").value = thisRow.childNodes[2].innerText;
            document.getElementById("userInputBirthdate").innerText = thisRow.childNodes[3].innerText;
            document.getElementById("userInputBirthdate").value = thisRow.childNodes[3].innerText;
            // document.getElementById("userInputGender").value = thisRow.childNodes[4].innerText;
            if (thisRow.childNodes[4].innerText == "") {
                document.getElementById("userInputGender").value = null;
            }
            else if (thisRow.childNodes[4].innerText === 'male') {
                document.getElementById("userInputGender").value = 0;
                document.getElementById("userInputGender")[0].selected = "true";
            }
            else {
                document.getElementById("userInputGender").value = 1;
                document.getElementById("userInputGender")[1].selected = "true";
            }

            document.getElementById("userInputButton").value = "Изменить пользователя";
            // if (document.getElementById("userInputGender")[].value == null){
            //
            //     document.getElementById("userInputGender").value = thisRow.childNodes[4].innerText;
            // } else if (document.getElementById("userInputGender").value != thisRow.childNodes[4].innerText){
            //     document.getElementById("userInputGender")[1].selected = "true";
            //     document.getElementById("userInputGender").value = thisRow.childNodes[4].innerText;
            // } else {document.getElementById("userInputGender").value=null}

        }
    </script>
</div>
</body>
</html>