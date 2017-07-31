import geb.Browser
import geb.Page

/**
 * created by nastane on 7/31/17.
 */
class TwitterLoginPage extends Page {

    static url = "https://twitter.com"

    static at = { title == "Twitter. It's what's happening" }

    static content = {
        emailField { $("input[id=signin-email]") }
        passwordField { $("input[id=signin-password]") }
        LogInButton(to: HomeTimeline) { $("button[type=submit]").firstElement() }
    }
}

class HomeTimeline extends Page {
    static at = { $("h1").text() == "Twitter"}
    static content = {
        tweetUserLink(to: UserPage) { $("a[data-element-term=tweet_stats]") }
        tweets { $("div[data-tweet-id]") }
        tweet { index -> tweets[index] }
        firstTweet { $("div[data-tweet-id]").firstElement() }
    }

    void openUserTweets() {
        tweetUserLink.click()
    }

    String getHomeTimeLineTweet(int index, String attribute) {
        return tweet[index].getAttribute(attribute)
    }
}

class UserPage extends Page {
    static at = { title == "Taccount (@interviewtest2) | Twitter" }
    static content = {
        tweets { $("div[data-tweet-id]") }
        tweet { index -> tweets[index] }
    }

    String getUserTweet(int index, String attribute) {
        return tweet[index].getAttribute(attribute)
    }
}

Browser.drive {
    to TwitterLoginPage

    emailField.value("NastaNe@ukr.net")
    passwordField.value("testtest123")
    LogInButton.click()
    assert browser.page.find("h1").text() == "Twitter"

    browser.page.find("a[data-element-term=tweet_stats]").firstElement().click()

    assert browser.page.find("div[data-you-follow=true]").firstElement().getAttribute("data-tweet-id").contains("892029027279679490")
}


