#!groovy
def BN = BRANCH_NAME == "master" || BRANCH_NAME.startsWith("releases/") ? BRANCH_NAME : "master"

library "knime-pipeline@$BN"

properties([
	pipelineTriggers([
		upstream('knime-javasnippet/' + env.BRANCH_NAME.replaceAll('/', '%2F')),
		upstream('knime-svg/' + env.BRANCH_NAME.replaceAll('/', '%2F')),
		upstream('knime-js-core/' + env.BRANCH_NAME.replaceAll('/', '%2F'))
	]),
	buildDiscarder(logRotator(numToKeepStr: '5')),
	disableConcurrentBuilds()
])

try {
	knimetools.defaultTychoBuild('org.knime.update.stats')

    workflowTests.runTests(
        dependencies: [ repositories: [
            'knime-stats', 'knime-python', 'knime-filehandling', 'knime-r', 'knime-js-core',
            'knime-js-base', 'knime-database', 'knime-kerberos', 'knime-jep', 'knime-xml',
            'knime-pmml', 'knime-expressions', 'knime-ensembles', 'knime-distance',
            'knime-datageneration', 'knime-chromium']
        ]
    )

    stage('Sonarqube analysis') {
           env.lastStage = env.STAGE_NAME
           workflowTests.runSonar()
    }
} catch (ex) {
    currentBuild.result = 'FAILURE'
    throw ex
} finally {
    notifications.notifyBuild(currentBuild.result);
}
/* vim: set shiftwidth=4 expandtab smarttab: */
