<#import "commons.ftlh" as c>

<@c.page>
    <div class="container mt-5 mb-5">
        <h1>Edit About me</h1>
        <form method="post">
            <textarea name="aboutText" placeholder="Write post text"
                      class="form-control">${user.aboutText}</textarea><br>
            <button type="submit" class="btn btn-success">Update</button>
        </form>
    </div>
</@c.page>