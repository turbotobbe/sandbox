<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="text" indent="yes" omit-xml-declaration="yes"/>
  <!--  <xsl:strip-space elements="*"/> -->

  <xsl:param name="time" />

  <xsl:template match="/">
    <xsl:apply-templates select="html/body/table/tr/td/table[@class='sharelistTable' and position()=1]" />
  </xsl:template>

  <xsl:template match="table">
    <xsl:apply-templates select="tr[position()>1]" />
  </xsl:template>

  <!--
      key atime btime volume latest buy sell
  -->
<xsl:template match="tr">
  <xsl:value-of select="translate(td[position()=1],' .åäöABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖ','--aaoabcdefghijklmnopqrstuvwxyzaao')" />,<xsl:value-of select="$time" />,<xsl:value-of select="td[position()=11]" />,<xsl:value-of select="td[position()=9]" />,<xsl:value-of select="td[position()=4]" />,<xsl:value-of select="td[position()=5]" />,<xsl:value-of select="td[position()=6]" /><xsl:text>
</xsl:text>
</xsl:template>

</xsl:stylesheet>
