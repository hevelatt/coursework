
namespace _07_Array
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.fileToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.panelPohja = new System.Windows.Forms.Panel();
            this.tableLayoutPanelPohja = new System.Windows.Forms.TableLayoutPanel();
            this.tableLayoutPanelSisalto = new System.Windows.Forms.TableLayoutPanel();
            this.labelLuonti = new System.Windows.Forms.Label();
            this.tableLayoutPanelLuonti = new System.Windows.Forms.TableLayoutPanel();
            this.label2 = new System.Windows.Forms.Label();
            this.textBoxLuontiKoko = new System.Windows.Forms.TextBox();
            this.buttonLuoTaulukko = new System.Windows.Forms.Button();
            this.labelTallennus = new System.Windows.Forms.Label();
            this.tableLayoutPanelTallennus = new System.Windows.Forms.TableLayoutPanel();
            this.label4 = new System.Windows.Forms.Label();
            this.textBoxTallennusNumero = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.textBoxTallennusIndeksi = new System.Windows.Forms.TextBox();
            this.buttonTallenna = new System.Windows.Forms.Button();
            this.labelHaku = new System.Windows.Forms.Label();
            this.tableLayoutPanelHaku = new System.Windows.Forms.TableLayoutPanel();
            this.label7 = new System.Windows.Forms.Label();
            this.textBoxHakuIndeksi = new System.Windows.Forms.TextBox();
            this.buttonHae = new System.Windows.Forms.Button();
            this.labelHakutulos = new System.Windows.Forms.Label();
            this.menuStrip1.SuspendLayout();
            this.panelPohja.SuspendLayout();
            this.tableLayoutPanelPohja.SuspendLayout();
            this.tableLayoutPanelSisalto.SuspendLayout();
            this.tableLayoutPanelLuonti.SuspendLayout();
            this.tableLayoutPanelTallennus.SuspendLayout();
            this.tableLayoutPanelHaku.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.BackColor = System.Drawing.SystemColors.ControlLight;
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.fileToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.RenderMode = System.Windows.Forms.ToolStripRenderMode.System;
            this.menuStrip1.Size = new System.Drawing.Size(490, 24);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // fileToolStripMenuItem
            // 
            this.fileToolStripMenuItem.Name = "fileToolStripMenuItem";
            this.fileToolStripMenuItem.Size = new System.Drawing.Size(37, 20);
            this.fileToolStripMenuItem.Text = "File";
            // 
            // panelPohja
            // 
            this.panelPohja.BackColor = System.Drawing.SystemColors.ControlLightLight;
            this.panelPohja.Controls.Add(this.tableLayoutPanelPohja);
            this.panelPohja.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelPohja.Location = new System.Drawing.Point(0, 24);
            this.panelPohja.Name = "panelPohja";
            this.panelPohja.Size = new System.Drawing.Size(490, 337);
            this.panelPohja.TabIndex = 1;
            // 
            // tableLayoutPanelPohja
            // 
            this.tableLayoutPanelPohja.AutoSize = true;
            this.tableLayoutPanelPohja.ColumnCount = 1;
            this.tableLayoutPanelPohja.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanelPohja.Controls.Add(this.tableLayoutPanelSisalto, 0, 0);
            this.tableLayoutPanelPohja.Dock = System.Windows.Forms.DockStyle.Top;
            this.tableLayoutPanelPohja.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanelPohja.Name = "tableLayoutPanelPohja";
            this.tableLayoutPanelPohja.RowCount = 1;
            this.tableLayoutPanelPohja.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanelPohja.Size = new System.Drawing.Size(490, 268);
            this.tableLayoutPanelPohja.TabIndex = 1;
            // 
            // tableLayoutPanelSisalto
            // 
            this.tableLayoutPanelSisalto.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.tableLayoutPanelSisalto.AutoSize = true;
            this.tableLayoutPanelSisalto.AutoSizeMode = System.Windows.Forms.AutoSizeMode.GrowAndShrink;
            this.tableLayoutPanelSisalto.ColumnCount = 1;
            this.tableLayoutPanelSisalto.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelSisalto.Controls.Add(this.labelLuonti, 0, 0);
            this.tableLayoutPanelSisalto.Controls.Add(this.tableLayoutPanelLuonti, 0, 1);
            this.tableLayoutPanelSisalto.Controls.Add(this.labelTallennus, 0, 2);
            this.tableLayoutPanelSisalto.Controls.Add(this.tableLayoutPanelTallennus, 0, 3);
            this.tableLayoutPanelSisalto.Controls.Add(this.labelHaku, 0, 4);
            this.tableLayoutPanelSisalto.Controls.Add(this.tableLayoutPanelHaku, 0, 5);
            this.tableLayoutPanelSisalto.Location = new System.Drawing.Point(107, 0);
            this.tableLayoutPanelSisalto.Margin = new System.Windows.Forms.Padding(0);
            this.tableLayoutPanelSisalto.Name = "tableLayoutPanelSisalto";
            this.tableLayoutPanelSisalto.RowCount = 6;
            this.tableLayoutPanelSisalto.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanelSisalto.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanelSisalto.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanelSisalto.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanelSisalto.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanelSisalto.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanelSisalto.Size = new System.Drawing.Size(276, 268);
            this.tableLayoutPanelSisalto.TabIndex = 0;
            // 
            // labelLuonti
            // 
            this.labelLuonti.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.labelLuonti.AutoSize = true;
            this.labelLuonti.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Underline, System.Drawing.GraphicsUnit.Point);
            this.labelLuonti.Location = new System.Drawing.Point(83, 12);
            this.labelLuonti.Margin = new System.Windows.Forms.Padding(3, 12, 3, 6);
            this.labelLuonti.Name = "labelLuonti";
            this.labelLuonti.Size = new System.Drawing.Size(110, 15);
            this.labelLuonti.TabIndex = 0;
            this.labelLuonti.Text = "TAULUKON LUONTI";
            // 
            // tableLayoutPanelLuonti
            // 
            this.tableLayoutPanelLuonti.AutoSize = true;
            this.tableLayoutPanelLuonti.ColumnCount = 3;
            this.tableLayoutPanelLuonti.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelLuonti.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelLuonti.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelLuonti.Controls.Add(this.label2, 0, 0);
            this.tableLayoutPanelLuonti.Controls.Add(this.textBoxLuontiKoko, 1, 0);
            this.tableLayoutPanelLuonti.Controls.Add(this.buttonLuoTaulukko, 2, 0);
            this.tableLayoutPanelLuonti.Location = new System.Drawing.Point(3, 36);
            this.tableLayoutPanelLuonti.Name = "tableLayoutPanelLuonti";
            this.tableLayoutPanelLuonti.RowCount = 1;
            this.tableLayoutPanelLuonti.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelLuonti.Size = new System.Drawing.Size(244, 31);
            this.tableLayoutPanelLuonti.TabIndex = 1;
            // 
            // label2
            // 
            this.label2.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(3, 8);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(87, 15);
            this.label2.TabIndex = 0;
            this.label2.Text = "Taulukon koko:";
            // 
            // textBoxLuontiKoko
            // 
            this.textBoxLuontiKoko.Location = new System.Drawing.Point(96, 3);
            this.textBoxLuontiKoko.Margin = new System.Windows.Forms.Padding(3, 3, 6, 3);
            this.textBoxLuontiKoko.Name = "textBoxLuontiKoko";
            this.textBoxLuontiKoko.Size = new System.Drawing.Size(50, 23);
            this.textBoxLuontiKoko.TabIndex = 1;
            // 
            // buttonLuoTaulukko
            // 
            this.buttonLuoTaulukko.AutoSize = true;
            this.buttonLuoTaulukko.Location = new System.Drawing.Point(155, 3);
            this.buttonLuoTaulukko.Name = "buttonLuoTaulukko";
            this.buttonLuoTaulukko.Size = new System.Drawing.Size(86, 25);
            this.buttonLuoTaulukko.TabIndex = 2;
            this.buttonLuoTaulukko.Text = "Luo taulukko";
            this.buttonLuoTaulukko.UseVisualStyleBackColor = true;
            this.buttonLuoTaulukko.Click += new System.EventHandler(this.buttonLuoTaulukko_Click);
            // 
            // labelTallennus
            // 
            this.labelTallennus.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.labelTallennus.AutoSize = true;
            this.labelTallennus.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Underline, System.Drawing.GraphicsUnit.Point);
            this.labelTallennus.Location = new System.Drawing.Point(103, 82);
            this.labelTallennus.Margin = new System.Windows.Forms.Padding(3, 12, 3, 6);
            this.labelTallennus.Name = "labelTallennus";
            this.labelTallennus.Size = new System.Drawing.Size(70, 15);
            this.labelTallennus.TabIndex = 0;
            this.labelTallennus.Text = "TALLENNUS";
            // 
            // tableLayoutPanelTallennus
            // 
            this.tableLayoutPanelTallennus.AutoSize = true;
            this.tableLayoutPanelTallennus.ColumnCount = 3;
            this.tableLayoutPanelTallennus.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelTallennus.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelTallennus.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelTallennus.Controls.Add(this.label4, 0, 0);
            this.tableLayoutPanelTallennus.Controls.Add(this.textBoxTallennusNumero, 1, 0);
            this.tableLayoutPanelTallennus.Controls.Add(this.label5, 0, 1);
            this.tableLayoutPanelTallennus.Controls.Add(this.textBoxTallennusIndeksi, 1, 1);
            this.tableLayoutPanelTallennus.Controls.Add(this.buttonTallenna, 2, 1);
            this.tableLayoutPanelTallennus.Location = new System.Drawing.Point(3, 106);
            this.tableLayoutPanelTallennus.Name = "tableLayoutPanelTallennus";
            this.tableLayoutPanelTallennus.RowCount = 2;
            this.tableLayoutPanelTallennus.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanelTallennus.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanelTallennus.Size = new System.Drawing.Size(270, 60);
            this.tableLayoutPanelTallennus.TabIndex = 1;
            // 
            // label4
            // 
            this.label4.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(3, 7);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(114, 15);
            this.label4.TabIndex = 0;
            this.label4.Text = "Talletettava numero:";
            // 
            // textBoxTallennusNumero
            // 
            this.textBoxTallennusNumero.Location = new System.Drawing.Point(133, 3);
            this.textBoxTallennusNumero.Margin = new System.Windows.Forms.Padding(3, 3, 6, 3);
            this.textBoxTallennusNumero.Name = "textBoxTallennusNumero";
            this.textBoxTallennusNumero.Size = new System.Drawing.Size(50, 23);
            this.textBoxTallennusNumero.TabIndex = 1;
            // 
            // label5
            // 
            this.label5.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(3, 37);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(124, 15);
            this.label5.TabIndex = 0;
            this.label5.Text = "Talletuspaikan indeksi:";
            // 
            // textBoxTallennusIndeksi
            // 
            this.textBoxTallennusIndeksi.Location = new System.Drawing.Point(133, 32);
            this.textBoxTallennusIndeksi.Margin = new System.Windows.Forms.Padding(3, 3, 6, 3);
            this.textBoxTallennusIndeksi.Name = "textBoxTallennusIndeksi";
            this.textBoxTallennusIndeksi.Size = new System.Drawing.Size(50, 23);
            this.textBoxTallennusIndeksi.TabIndex = 1;
            // 
            // buttonTallenna
            // 
            this.buttonTallenna.AutoSize = true;
            this.buttonTallenna.Location = new System.Drawing.Point(192, 32);
            this.buttonTallenna.Name = "buttonTallenna";
            this.buttonTallenna.Size = new System.Drawing.Size(75, 25);
            this.buttonTallenna.TabIndex = 2;
            this.buttonTallenna.Text = "Talleta";
            this.buttonTallenna.UseVisualStyleBackColor = true;
            this.buttonTallenna.Click += new System.EventHandler(this.buttonTallenna_Click);
            // 
            // labelHaku
            // 
            this.labelHaku.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.labelHaku.AutoSize = true;
            this.labelHaku.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Underline, System.Drawing.GraphicsUnit.Point);
            this.labelHaku.Location = new System.Drawing.Point(118, 181);
            this.labelHaku.Margin = new System.Windows.Forms.Padding(3, 12, 3, 6);
            this.labelHaku.Name = "labelHaku";
            this.labelHaku.Size = new System.Drawing.Size(39, 15);
            this.labelHaku.TabIndex = 0;
            this.labelHaku.Text = "HAKU";
            // 
            // tableLayoutPanelHaku
            // 
            this.tableLayoutPanelHaku.AutoSize = true;
            this.tableLayoutPanelHaku.ColumnCount = 3;
            this.tableLayoutPanelHaku.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelHaku.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelHaku.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelHaku.Controls.Add(this.label7, 0, 0);
            this.tableLayoutPanelHaku.Controls.Add(this.textBoxHakuIndeksi, 1, 0);
            this.tableLayoutPanelHaku.Controls.Add(this.buttonHae, 2, 0);
            this.tableLayoutPanelHaku.Controls.Add(this.labelHakutulos, 1, 1);
            this.tableLayoutPanelHaku.Location = new System.Drawing.Point(3, 205);
            this.tableLayoutPanelHaku.Name = "tableLayoutPanelHaku";
            this.tableLayoutPanelHaku.RowCount = 2;
            this.tableLayoutPanelHaku.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanelHaku.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanelHaku.Size = new System.Drawing.Size(270, 60);
            this.tableLayoutPanelHaku.TabIndex = 1;
            // 
            // label7
            // 
            this.label7.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(3, 8);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(124, 15);
            this.label7.TabIndex = 0;
            this.label7.Text = "Talletuspaikan indeksi:";
            // 
            // textBoxHakuIndeksi
            // 
            this.textBoxHakuIndeksi.Location = new System.Drawing.Point(133, 3);
            this.textBoxHakuIndeksi.Margin = new System.Windows.Forms.Padding(3, 3, 6, 3);
            this.textBoxHakuIndeksi.Name = "textBoxHakuIndeksi";
            this.textBoxHakuIndeksi.Size = new System.Drawing.Size(50, 23);
            this.textBoxHakuIndeksi.TabIndex = 1;
            // 
            // buttonHae
            // 
            this.buttonHae.AutoSize = true;
            this.buttonHae.Location = new System.Drawing.Point(192, 3);
            this.buttonHae.Name = "buttonHae";
            this.buttonHae.Size = new System.Drawing.Size(75, 25);
            this.buttonHae.TabIndex = 2;
            this.buttonHae.Text = "Hae";
            this.buttonHae.UseVisualStyleBackColor = true;
            this.buttonHae.Click += new System.EventHandler(this.buttonHae_Click);
            // 
            // labelHakutulos
            // 
            this.labelHakutulos.AutoSize = true;
            this.labelHakutulos.BackColor = System.Drawing.Color.LightCyan;
            this.labelHakutulos.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.labelHakutulos.Font = new System.Drawing.Font("Arial", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.labelHakutulos.Location = new System.Drawing.Point(133, 37);
            this.labelHakutulos.Margin = new System.Windows.Forms.Padding(3, 6, 3, 3);
            this.labelHakutulos.Name = "labelHakutulos";
            this.labelHakutulos.Size = new System.Drawing.Size(19, 20);
            this.labelHakutulos.TabIndex = 3;
            this.labelHakutulos.Text = "0";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(490, 361);
            this.Controls.Add(this.panelPohja);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "Form1";
            this.Text = "Pääformi";
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.panelPohja.ResumeLayout(false);
            this.panelPohja.PerformLayout();
            this.tableLayoutPanelPohja.ResumeLayout(false);
            this.tableLayoutPanelPohja.PerformLayout();
            this.tableLayoutPanelSisalto.ResumeLayout(false);
            this.tableLayoutPanelSisalto.PerformLayout();
            this.tableLayoutPanelLuonti.ResumeLayout(false);
            this.tableLayoutPanelLuonti.PerformLayout();
            this.tableLayoutPanelTallennus.ResumeLayout(false);
            this.tableLayoutPanelTallennus.PerformLayout();
            this.tableLayoutPanelHaku.ResumeLayout(false);
            this.tableLayoutPanelHaku.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem fileToolStripMenuItem;
        private System.Windows.Forms.Panel panelPohja;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelSisalto;
        private System.Windows.Forms.Label labelLuonti;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelLuonti;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox textBoxLuontiKoko;
        private System.Windows.Forms.Button buttonLuoTaulukko;
        private System.Windows.Forms.Label labelTallennus;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelTallennus;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox textBoxTallennusNumero;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox textBoxTallennusIndeksi;
        private System.Windows.Forms.Button buttonTallenna;
        private System.Windows.Forms.Label labelHaku;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelHaku;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox textBoxHakuIndeksi;
        private System.Windows.Forms.Button buttonHae;
        private System.Windows.Forms.Label labelHakutulos;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelPohja;
    }
}

