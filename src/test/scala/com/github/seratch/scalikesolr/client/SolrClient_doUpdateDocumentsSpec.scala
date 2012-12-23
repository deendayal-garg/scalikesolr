package com.github.seratch.scalikesolr.client

import com.github.seratch.scalikesolr.request.common.WriterType
import com.github.seratch.scalikesolr.request.UpdateRequest
import java.net.URL
import com.github.seratch.scalikesolr.{ SolrDocumentValue, Solr, SolrDocument }
import org.slf4j.LoggerFactory
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class SolrClient_doUpdateDocumentsSpec extends FlatSpec with ShouldMatchers {

  behavior of "SolrClient#doUpdateDocuments"

  val log = LoggerFactory.getLogger("com.github.seratch.scalikesolr.SolrClientSpec")
  val client = Solr.httpServer(new URL("http://localhost:8983/solr")).newClient()

  it should "be available" in {
    val client = Solr.httpServer(new URL("http://localhost:8983/solr")).newClient()
    val request = new UpdateRequest()
    val doc1 = SolrDocument(
      writerType = WriterType.Standard,
      map = Map(
        "id" -> SolrDocumentValue("978-0641723445"),
        "cat" -> SolrDocumentValue("List(book, hardcover)"),
        "title" -> SolrDocumentValue("The Lightning Thief"),
        "author" -> SolrDocumentValue("Rick Riordan"),
        "series_t" -> SolrDocumentValue("Percy Jackson and the Olympians"),
        "sequence_i" -> SolrDocumentValue("1"),
        "genre_s" -> SolrDocumentValue("fantasy"),
        "inStock" -> SolrDocumentValue("true"),
        "price" -> SolrDocumentValue("12.50"),
        "pages_i" -> SolrDocumentValue("384"),
        "timestamp" -> SolrDocumentValue("2006-03-21T13:40:15.518Z")
      )
    )
    val doc2 = SolrDocument(
      writerType = WriterType.Standard,
      map = Map(
        "id" -> SolrDocumentValue("978-1423103349"),
        "cat" -> SolrDocumentValue("List(book, paperback)"),
        "title" -> SolrDocumentValue("The Sea of Monsters"),
        "author" -> SolrDocumentValue("Rick Riordan"),
        "series_t" -> SolrDocumentValue("Percy Jackson and the Olympians"),
        "sequence_i" -> SolrDocumentValue("2"),
        "genre_s" -> SolrDocumentValue("fantasy"),
        "inStock" -> SolrDocumentValue("true"),
        "price" -> SolrDocumentValue("6.49"),
        "pages_i" -> SolrDocumentValue("304"),
        "timestamp" -> SolrDocumentValue("2006-03-21T13:40:15.518Z")
      )

    )
    request.documents = List(doc1, doc2)
    val response = client.doUpdateDocuments(request)
    client.doCommit(new UpdateRequest())

    response should not be null
    response.responseHeader.status should equal(0)
    response.responseHeader.qTime should be > 0
    response.rawBody.replaceAll("\r", "").replaceAll("\n", "").trim should fullyMatch regex """<\?xml version="1.0" encoding="UTF-8"\?>
                                                                                              |<response>
                                                                                              |<lst name="responseHeader"><int name="status">0</int><int name="QTime">\d+</int></lst>
                                                                                              |</response>
                                                                                              | """.stripMargin.replaceAll("\r", "").replaceAll("\n", "").trim
  }

}
