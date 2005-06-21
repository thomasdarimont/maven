package org.apache.maven.artifact.repository;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.metadata.ArtifactMetadata;
import org.apache.maven.artifact.repository.layout.ArtifactRepositoryLayout;
import org.apache.maven.wagon.repository.Repository;

/**
 * This class is an abstraction of the location from/to resources can be
 * transfered.
 *
 * @author <a href="michal.maczka@dimatics.com">Michal Maczka </a>
 * @version $Id$
 */
public class DefaultArtifactRepository
    extends Repository
    implements ArtifactRepository
{
    private final String snapshotPolicy;
    
    private final String checksumPolicy;

    private final ArtifactRepositoryLayout layout;

    public DefaultArtifactRepository( String id, String url, ArtifactRepositoryLayout layout )
    {
        this( id, url, layout, SNAPSHOT_POLICY_NEVER, CHECKSUM_POLICY_WARN );
    }

    public DefaultArtifactRepository( String id, String url, ArtifactRepositoryLayout layout, String snapshotPolicy, String checksumPolicy )
    {
        super( id, url );

        this.layout = layout;

        this.snapshotPolicy = snapshotPolicy;
        
        this.checksumPolicy = checksumPolicy;
    }

    public String pathOf( Artifact artifact )
    {
        return layout.pathOf( artifact );
    }

    public String pathOfMetadata( ArtifactMetadata artifactMetadata )
    {
        return layout.pathOfMetadata( artifactMetadata );
    }

    public String getSnapshotPolicy()
    {
        return snapshotPolicy;
    }
    
    public String getChecksumPolicy()
    {
        return checksumPolicy;
    }
    
    public boolean failOnChecksumMismatch()
    {
        return CHECKSUM_POLICY_FAIL.equals( checksumPolicy );
    }

}