<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<html lang="ru">
<head>
    <title>Users&Cars</title>
    <meta charset="utf-8">
    <style>
        th {
            font-weight: normal;
            color: #039;
            padding: 10px 15px;
            border-right: 1px solid #f6c163;
        }
        #userTable td {
            color: #131111;
            border-top: 1px solid #cbb04b;
            padding: 10px 15px;
        }
        #userTable .userTableRow:nth-child(2n) {
            background: #ffe0b9;
        }

        #userTable .userTableRow:hover td {
            background: #ccddff;
        }
        /*.carTable {*/
        /*    background: #f6c163;*/
        /*}*/

        table {
            width: 100%;
            border-collapse: collapse;
            table-layout: fixed;
            border: 1px black solid;
            padding: 0;
            /*background-color: bisque;*/

        }

        #InputCarTable {
            margin-top: -1px;
        }

        #userFormTable{
            margin-top: -1px;
            overflow: hidden;
        }

        #userFormTable td {
            padding: 3px 3px ;
            border-right: 1px black solid;
            overflow:hidden;
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
            width: 18%;
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
        #thUserDelete, #tdUserFormDelete{
            width: 3%;
        }
        tr {
            vertical-align: top;
        }

        #userTable td{
            margin: 0;
            padding: 3px;
            border-right: 1px black solid;
            overflow:hidden;
        }

        #userTable{
            border: 1px black solid;
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
            padding: 0 0;


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
            <th id="thUserDelete">x</th>
            <th id="thUserCarCollection">car collection</th>
        </tr>
    </table>
    <script type="text/javascript">

        javaList = ${userList};
        javaList.forEach(function(itemUser, i, arrUser) {
            $('#userTable').append($('<tr class="userTableRow" onclick="selectUser(this)">')
                .append($('<td>').append(itemUser.id))
                .append($('<td>').append(itemUser.name))
                .append($('<td>').append(itemUser.email))
                .append($('<td>').append(itemUser.birthdate))
                .append($('<td>').append(itemUser.gender))
                .append($('<td>').append('<input type="button" value="X" onclick="deleteUser(this)">'))
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
    <table id="userFormTable">
        <tr>
            <form id="benderform" action="/" method="post">
                <td id="tdUserFormId"><input type="text" id="userInputId" name="id" value="0" readonly></td>
                <td id="tdUserFormName"><input type="text" id="userInputName" name="name"></td>
                <td id="tdUserFormEmail"><input type="email" id="userInputEmail" name="email"></td>
                <td id="tdUserFormBirthdate"><input type="date" id="userInputBirthdate" name="birthdate" data-date-format="YYYY-MMMM-DD"></td>
                <td id="tdUserFormGender"><select id="userInputGender" name="gender" value="">
                                    <option value="0" name="male" >male</option>
                                    <option value="1" >female</option>
                                </select>
                    </td>
                <td id="tdUserFormDelete"><input type="submit" id="userInputButton" value="OK"></td>
            </form>
            <td id="tdUserFormCarCollection"></td>
        </tr>
    </table>



    <script type="text/javascript" charset="utf-8">
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
            console.log( JSON.stringify(obj));
            // Отправляем (асинхронно!)
            fetch(request).then(
                function(response) {
                    // Запрос успешно выполнен
                    console.log(response);
                    // return response.json() и так далее см. документацию
                    // location.reload(true); /*true - загрузка с сервера , false - с кеша*/
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

        function deleteUser(thisUser){
            userId = thisUser.parentElement.parentElement.firstChild.innerText;
            console.log(userId);

            var xhr = new XMLHttpRequest();

            xhr.open('DELETE', '/'+userId, true);

            xhr.send();

            xhr.onreadystatechange = function() {
                if (this.readyState != 4) {
                    location.reload(true); /*true - загрузка с сервера , false - с кеша*/
                    return;
                }
                // по окончании запроса доступны:
                // status, statusText
                // responseText, responseXML (при content-type: text/xml)
                if (this.status != 200) {
                    // обработать ошибку
                    alert( 'ошибка: ' + (this.status ? this.statusText : 'запрос не удался') );
                    return;
                }
                // получить результат из this.responseText или this.responseXML
            }
        }
    </script>

    <script type="text/javascript" charset="utf-8">
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

            document.getElementById("userInputButton").value = "OK";


            //здесь добаавляем таблицу-редактор машин
            document.getElementById("tdUserFormCarCollection").innerHTML = thisRow.lastChild.innerHTML;
            /*$('#tdUserFormCarCollection').firstChild
                .append($('<td>').innerHTML = '<input type="button" value="x">');*/

            trArr = document.getElementById("tdUserFormCarCollection").getElementsByTagName('tr');

            for ( i = 0, l = trArr.length; i < l; i++){
                trArr[i].setAttribute("onclick","selectCar(this)");///здесь дописать функцию заполнения формы машин
                trArr[i].insertCell(4).innerHTML = '<input type="button" value="x" onclick="deleteCar(this)">';
            }

            $('#tdUserFormCarCollection')
                .append($('<form id="carForm" action="/cars" method="post" >')
                .append($('<table id="InputCarTable">')
                    .append($('<tr>')
                        .append($('<th>').append('id'))
                        .append($('<th>').append('brend'))
                        .append($('<th>').append('mark'))
                        .append($('<th>').append('year'))
                        .append($('<th>').append('owner id'))
                        .append($('<th>').append(''))
                    )
                    .append($('<tr>')
                        .append($('<td>').append($('<input type="text" id="carInputId" name="id" value="0" readonly>')))
                        .append($('<td>').append($('<input type="text" id="carInputBrend" name="brend">')))
                        .append($('<td>').append($('<input type="text" id="carInputModel" name="model">')))
                        .append($('<td>').append($('<input type="text" id="carInputYearOfRelease" name="year_of_release">')))
                        .append($('<td>').append($('<input type="text" id="carInputOwnerId" name="owner_id">')))
                        .append($('<td>').append($('<input type="submit" id="carInputButton" value="OK">')))
                    )
                )
            );

            document.getElementById("carInputOwnerId").innerText = document.getElementById("userInputId").innerText;
            document.getElementById("carInputOwnerId").value = document.getElementById("userInputId").value;

            document.getElementById('carForm').addEventListener('submit', submitForm1);
        }

        function selectCar(thisRow){
            document.getElementById("carInputId").innerText = thisRow.firstChild.innerText;
            document.getElementById("carInputId").value = thisRow.firstChild.innerText;
            document.getElementById("carInputBrend").innerText = thisRow.childNodes[1].innerText;
            document.getElementById("carInputBrend").value = thisRow.childNodes[1].innerText;
            document.getElementById("carInputModel").innerText = thisRow.childNodes[2].innerText;
            document.getElementById("carInputModel").value = thisRow.childNodes[2].innerText;
            document.getElementById("carInputYearOfRelease").innerText = thisRow.childNodes[3].innerText;
            document.getElementById("carInputYearOfRelease").value = thisRow.childNodes[3].innerText;
            document.getElementById("carInputOwnerId").innerText = document.getElementById("userInputId").innerText;
            document.getElementById("carInputOwnerId").value = document.getElementById("userInputId").value;

            // document.getElementById('carForm').addEventListener('submit', submitForm1);
        }

    </script>

    <script type="text/javascript" charset="utf-8">
        //************************************Переопределение метода отправки формы машины******************************
        // document.getElementById('carForm').addEventListener('submit', submitForm1);


        function submitForm1(event) {
            // Отменяем стандартное поведение браузера с отправкой формы
            event.preventDefault();

            // event.target — это HTML-элемент form
            let formData = new FormData(event.target);

            // Собираем данные формы в объект
            let obj = {};
            formData.forEach((value, key) => obj[key] = value);
            // Собираем запрос к серверу
            alert(obj);
            let request = new Request(event.target.action, {
                method: 'POST',
                body: JSON.stringify(obj),
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            alert(JSON.stringify(obj));
            // Отправляем (асинхронно!)
            fetch(request).then(
                function(response) {
                    // Запрос успешно выполнен
                    console.log(response);
                    // return response.json();// и так далее см. документацию
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

        function deleteCar(thisCar){
            carId = thisCar.parentElement.parentElement.firstChild.innerText;
            console.log(carId);

            var xhr = new XMLHttpRequest();

            xhr.open('DELETE', '/cars/'+carId, true);

            xhr.send();

            xhr.onreadystatechange = function() {
                if (this.readyState != 4) {
                    location.reload(true); /*true - загрузка с сервера , false - с кеша*/
                    return;
                }
                // по окончании запроса доступны:
                // status, statusText
                // responseText, responseXML (при content-type: text/xml)
                if (this.status != 200) {
                    // обработать ошибку
                    alert( 'ошибка: ' + (this.status ? this.statusText : 'запрос не удался') );
                    return;
                }
                // получить результат из this.responseText или this.responseXML
            }
        }
    </script>

</div>
</body>
</html>