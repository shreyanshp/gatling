import io.gatling.core.Predef._
import io.gatling.core.validation._
import io.gatling.http.Predef._

class Checks {

  //#status-is-200
  http("My Request").get("myUrl").check(status.is(200))
  //#status-is-200

  //#status-is-not-404-or-500
  http("My Request").get("myUrl").check(status.not(404), status.not(500))
  //#status-is-not-404-or-500

  {
    type T = String
    val pattern, headerName, expression = ""

    //#currentLocationRegex-ofType
    currentLocationRegex(pattern).ofType[T]
    //#currentLocationRegex-ofType

    //#currentLocationRegex-example
    currentLocationRegex("http://foo.com/bar?(.*)=(.*)").ofType[(String, String)]
    //#currentLocationRegex-example

    //#headerRegex-ofType
    headerRegex(headerName, pattern).ofType[T]
    //#headerRegex-ofType

    //#headerRegex-example
    headerRegex("FOO", "foo(.*)bar(.*)baz").ofType[(String, String)]
    //#headerRegex-example

    //#substring
    substring("foo")                           // same as substring("foo").find.exists
    substring("foo").findAll.saveAs("indices") // saves a Seq[Int]
    substring("foo").count.saveAs("counts")    // saves the number of occurrences of foo
    //#substring

    //#regex
    regex("""<td class="number">""")
    regex("""<td class="number">ACC${account_id}</td>""")
    regex("""/private/bank/account/(ACC[0-9]*)/operations.html""")
    //#regex

    //#regex-ofType
    regex(expression).ofType[T]
    //#regex-ofType

    //#regex-example
    regex("foo(.*)bar(.*)baz").ofType[(String, String)]
    //#regex-example

    //#xpath
    xpath("//input[@id='text1']/@value")
    xpath("//foo:input[@id='text1']/@value", List("foo" -> "http://foo.com"))
    //#xpath

    //#jsonPath
    jsonPath("$..foo.bar[2].baz")
    //#jsonPath

    //#jsonPath-ofType
    jsonPath(expression).ofType[T]
    //#jsonPath-ofType

    val response =
      """
    //#json-response
    // JSON Response
    {
      "foo": 1,
      "bar" "baz"
    }
    //#json-response
    """

    //#jsonPath-Int

    jsonPath("$..foo").ofType[Int] // will match 1
    //#jsonPath-Int

    //#css
    css("article.more a", "href")
    //#css

    jsonPath("$..foo.bar[2].baz").
    //#transform
    transform(string => string + "foo")

    //#transform

    jsonPath("$..foo.bar[2].baz").
    //#transformOption
    transformOption(extract => extract.orElse(Some("default")).success)
    //#transformOption

    //#is
    status.is(200)
    //#is

    //#not
    status.not(500)
    //#not

    //#exists
    jsonPath("$..foo").exists
    //#exists

    //#notExists
    jsonPath("$..foo").notExists
    //#notExists

    //#in
    status.in(200, 304)
    //#in

    //#regex-count-is
    regex("""https://(.*)""").count.is(5)
    //#regex-count-is

    //#regex-findAll-is
    regex("""https://(.*)/.*""")
      .findAll
      .is(List("www.google.com", "www.mysecuredsite.com"))
    //#regex-findAll-is

    //#status-is
    status.is(200)
    //#status-is

    //#status-in
    status.in(200 to 210)
    //#status-in

    //#regex-find-exists
    regex("aWord").find(1).exists
    //#regex-find-exists

    //#regex-notExists
    regex("aWord").notExists
    //#regex-notExists

    //#bodyString-is-RawFileBody
    bodyString.is(RawFileBody("expected_response.json"))
    //#bodyString-is-RawFileBody

    //#bodyString-isELFileBody
    bodyString.is(ELFileBody("expected_template.json"))
    //#bodyString-isELFileBody
  }

}
