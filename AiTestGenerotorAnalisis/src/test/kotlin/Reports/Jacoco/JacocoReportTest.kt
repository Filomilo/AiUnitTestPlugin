package Reports.Jacoco



import com.fasterxml.jackson.databind.SerializationFeature
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco.JacocoReport
import org.junit.jupiter.api.Assertions.assertEquals

class JacocoReportTest {

    val xmlConversion="""
        <report name="JavaCalculator">
            <sessioninfo id="DESKTOP-HMGMU2J-80dea4a0" start="1755518872961" dump="1755518873898" />
            <package name="org/filomilo/AiTestGenerotorAnalisis">
                <class name="org/filomilo/AiTestGenerotorAnalisis/Calculator"
                    sourcefilename="Calculator.java">
                    <method name="&lt;init&gt;" desc="()V" line="3">
                        <counter type="INSTRUCTION" missed="3" covered="0" />
                        <counter type="LINE" missed="1" covered="0" />
                        <counter type="COMPLEXITY" missed="1" covered="0" />
                        <counter type="METHOD" missed="1" covered="0" />
                    </method>
                    <method name="add" desc="(II)I" line="6">
                        <counter type="INSTRUCTION" missed="0" covered="4" />
                        <counter type="LINE" missed="0" covered="1" />
                        <counter type="COMPLEXITY" missed="0" covered="1" />
                        <counter type="METHOD" missed="0" covered="1" />
                    </method>
                    <method name="subtract" desc="(II)I" line="9">
                        <counter type="INSTRUCTION" missed="0" covered="4" />
                        <counter type="LINE" missed="0" covered="1" />
                        <counter type="COMPLEXITY" missed="0" covered="1" />
                        <counter type="METHOD" missed="0" covered="1" />
                    </method>
                    <method name="multiply" desc="(II)I" line="12">
                        <counter type="INSTRUCTION" missed="0" covered="4" />
                        <counter type="LINE" missed="0" covered="1" />
                        <counter type="COMPLEXITY" missed="0" covered="1" />
                        <counter type="METHOD" missed="0" covered="1" />
                    </method>
                    <method name="divide" desc="(II)I" line="15">
                        <counter type="INSTRUCTION" missed="0" covered="11" />
                        <counter type="BRANCH" missed="0" covered="2" />
                        <counter type="LINE" missed="0" covered="3" />
                        <counter type="COMPLEXITY" missed="0" covered="2" />
                        <counter type="METHOD" missed="0" covered="1" />
                    </method>
                    <method name="multiply" desc="(FF)F" line="21">
                        <counter type="INSTRUCTION" missed="0" covered="4" />
                        <counter type="LINE" missed="0" covered="1" />
                        <counter type="COMPLEXITY" missed="0" covered="1" />
                        <counter type="METHOD" missed="0" covered="1" />
                    </method>
                    <method name="divide" desc="(FF)F" line="24">
                        <counter type="INSTRUCTION" missed="0" covered="13" />
                        <counter type="BRANCH" missed="0" covered="2" />
                        <counter type="LINE" missed="0" covered="3" />
                        <counter type="COMPLEXITY" missed="0" covered="2" />
                        <counter type="METHOD" missed="0" covered="1" />
                    </method>
                    <method name="add" desc="(FF)F" line="29">
                        <counter type="INSTRUCTION" missed="0" covered="4" />
                        <counter type="LINE" missed="0" covered="1" />
                        <counter type="COMPLEXITY" missed="0" covered="1" />
                        <counter type="METHOD" missed="0" covered="1" />
                    </method>
                    <method name="subtract" desc="(FF)F" line="32">
                        <counter type="INSTRUCTION" missed="0" covered="4" />
                        <counter type="LINE" missed="0" covered="1" />
                        <counter type="COMPLEXITY" missed="0" covered="1" />
                        <counter type="METHOD" missed="0" covered="1" />
                    </method>
                    <method name="multiply" desc="(DD)D" line="38">
                        <counter type="INSTRUCTION" missed="0" covered="4" />
                        <counter type="LINE" missed="0" covered="1" />
                        <counter type="COMPLEXITY" missed="0" covered="1" />
                        <counter type="METHOD" missed="0" covered="1" />
                    </method>
                    <method name="divide" desc="(DD)D" line="41">
                        <counter type="INSTRUCTION" missed="0" covered="13" />
                        <counter type="BRANCH" missed="0" covered="2" />
                        <counter type="LINE" missed="0" covered="3" />
                        <counter type="COMPLEXITY" missed="0" covered="2" />
                        <counter type="METHOD" missed="0" covered="1" />
                    </method>
                    <method name="add" desc="(DD)D" line="46">
                        <counter type="INSTRUCTION" missed="0" covered="4" />
                        <counter type="LINE" missed="0" covered="1" />
                        <counter type="COMPLEXITY" missed="0" covered="1" />
                        <counter type="METHOD" missed="0" covered="1" />
                    </method>
                    <method name="subtract" desc="(DD)D" line="49">
                        <counter type="INSTRUCTION" missed="0" covered="4" />
                        <counter type="LINE" missed="0" covered="1" />
                        <counter type="COMPLEXITY" missed="0" covered="1" />
                        <counter type="METHOD" missed="0" covered="1" />
                    </method>
                    <counter type="INSTRUCTION" missed="3" covered="73" />
                    <counter type="BRANCH" missed="0" covered="6" />
                    <counter type="LINE" missed="1" covered="18" />
                    <counter type="COMPLEXITY" missed="1" covered="15" />
                    <counter type="METHOD" missed="1" covered="12" />
                    <counter type="CLASS" missed="0" covered="1" />
                </class>
                <sourcefile name="Calculator.java">
                    <line nr="3" mi="3" ci="0" mb="0" cb="0" />
                    <line nr="6" mi="0" ci="4" mb="0" cb="0" />
                    <line nr="9" mi="0" ci="4" mb="0" cb="0" />
                    <line nr="12" mi="0" ci="4" mb="0" cb="0" />
                    <line nr="15" mi="0" ci="2" mb="0" cb="2" />
                    <line nr="16" mi="0" ci="5" mb="0" cb="0" />
                    <line nr="17" mi="0" ci="4" mb="0" cb="0" />
                    <line nr="21" mi="0" ci="4" mb="0" cb="0" />
                    <line nr="24" mi="0" ci="4" mb="0" cb="2" />
                    <line nr="25" mi="0" ci="5" mb="0" cb="0" />
                    <line nr="26" mi="0" ci="4" mb="0" cb="0" />
                    <line nr="29" mi="0" ci="4" mb="0" cb="0" />
                    <line nr="32" mi="0" ci="4" mb="0" cb="0" />
                    <line nr="38" mi="0" ci="4" mb="0" cb="0" />
                    <line nr="41" mi="0" ci="4" mb="0" cb="2" />
                    <line nr="42" mi="0" ci="5" mb="0" cb="0" />
                    <line nr="43" mi="0" ci="4" mb="0" cb="0" />
                    <line nr="46" mi="0" ci="4" mb="0" cb="0" />
                    <line nr="49" mi="0" ci="4" mb="0" cb="0" />
                    <counter type="INSTRUCTION" missed="3" covered="73" />
                    <counter type="BRANCH" missed="0" covered="6" />
                    <counter type="LINE" missed="1" covered="18" />
                    <counter type="COMPLEXITY" missed="1" covered="15" />
                    <counter type="METHOD" missed="1" covered="12" />
                    <counter type="CLASS" missed="0" covered="1" />
                </sourcefile>
                <counter type="INSTRUCTION" missed="3" covered="73" />
                <counter type="BRANCH" missed="0" covered="6" />
                <counter type="LINE" missed="1" covered="18" />
                <counter type="COMPLEXITY" missed="1" covered="15" />
                <counter type="METHOD" missed="1" covered="12" />
                <counter type="CLASS" missed="0" covered="1" />
            </package>
            <counter type="INSTRUCTION" missed="3" covered="73" />
            <counter type="BRANCH" missed="0" covered="6" />
            <counter type="LINE" missed="1" covered="18" />
            <counter type="COMPLEXITY" missed="1" covered="15" />
            <counter type="METHOD" missed="1" covered="12" />
            <counter type="CLASS" missed="0" covered="1" />
        </report>
    """;


    @Test
    fun XmlConversion() {

        val mapper: XmlMapper = XmlMapper()
        mapper.registerModules()
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        val JacocoReport: JacocoReport =   mapper.readValue(xmlConversion, JacocoReport::class.java)
        assertEquals(JacocoReport.name,"JavaCalculator")
        assertEquals(JacocoReport.sessioninfo.id,"DESKTOP-HMGMU2J-80dea4a0")
    }
}