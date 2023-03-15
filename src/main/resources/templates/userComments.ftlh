<#import "commons.ftlh" as c>

<@c.page>
    <div class="container mt-5">
        <#list allComments as comment>
            <@c.userComment comment=comment/>
        </#list>
    </div>
</@c.page>