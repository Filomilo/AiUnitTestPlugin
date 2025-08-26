package org.filomilo.AiTestGenerotorAnalisis.TestGeneration

import LLM.PromptInformationProvider
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import java.util.*



object PromptFormatter {
    /***
     * reolves arguemtns to be replaces in promopt handles
     *
     *
     * ##framework## - name of testing framework to use
     *
     * ##functions## - functions to ber replace by promop info provider
     *
     * ##classes## - classes to ber replace by promop info provider
     *
     *  ##files## - files to ber replace by promop info provider
     */
    fun resolveArguments( prompt:String, promptInformationProvider: PromptInformationProvider): String
    {

        val arguments: MutableMap<String, String> = mutableMapOf()

        if(promptInformationProvider.getTestingFramework()!=null)
        {
            arguments["##framework##"]=promptInformationProvider.getTestingFramework()
        }


        if(promptInformationProvider.getFunctions()!=null)
        {
            arguments["##functions##"]=promptInformationProvider.getFunctions()
        }

        if(promptInformationProvider.getClasses()!=null)
        {
            arguments["##classes##"]=promptInformationProvider.getClasses()
        }

        if(promptInformationProvider.getFiles()!=null)
        {
            arguments["##files##"]=promptInformationProvider.getFiles()
        }


        var promptCopy:String=prompt
        for (key:String in arguments.keys)
        {
            promptCopy=promptCopy.replace(key as String,arguments[key] as String,ignoreCase = true)
        }



        return promptCopy
    }
}