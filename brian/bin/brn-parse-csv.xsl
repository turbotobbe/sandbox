<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="text" indent="yes" omit-xml-declaration="yes"/>
  <!--  <xsl:strip-space elements="*"/> -->

  <xsl:param name="fetch.date" />
  <xsl:param name="fetch.time" />

  <xsl:template match="/">
    <xsl:apply-templates select="html/body/table/tr/td/table[@class='sharelistTable' and position()=1]" />
  </xsl:template>

  <xsl:template match="table">
    <xsl:apply-templates select="tr[position()>1]" />
  </xsl:template>

  <!--
      fetch.stamp fetch.date time volume latest buy sell key
  -->
<xsl:template match="tr">
  "fetch-date":"<xsl:value-of select="$fetch.date" />","fetch-time":"<xsl:value-of select="$fetch.time" />","time":"<xsl:value-of select="td[position()=11]" />","volume":"<xsl:value-of select="td[position()=9]" />","latest":"<xsl:value-of select="td[position()=4]" />","buy":"<xsl:value-of select="td[position()=5]" />","sell":"<xsl:value-of select="td[position()=6]" />","key":"<xsl:value-of select="translate(td[position()=1],' .åäöABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖ','--aaoabcdefghijklmnopqrstuvwxyzaao')" />"<xsl:text>
</xsl:text>
</xsl:template>

</xsl:stylesheet>
